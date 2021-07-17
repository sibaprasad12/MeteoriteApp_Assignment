package com.assignment.meteoriteapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.assignment.meteoriteapp.R
import com.assignment.meteoriteapp.controllers.OnMeteorClickListener
import com.assignment.meteoriteapp.data.Meteor
import com.assignment.meteoriteapp.databinding.FragmentMeteorBinding
import com.assignment.meteoriteapp.network.Status
import com.assignment.meteoriteapp.ui.MeteorActivity
import com.assignment.meteoriteapp.ui.adapter.MeteorAdapter
import com.assignment.meteoriteapp.ui.map.MeteorMapDialogFragment
import com.assignment.meteoriteapp.utils.CommonUtils
import com.assignment.meteoriteapp.utils.LinearLayoutManagerWithSmoothScroller
import com.assignment.meteoriteapp.utils.NetworkUtil
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint


/**
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class MeteorFragment : Fragment(), OnMeteorClickListener, SwipeRefreshLayout.OnRefreshListener {

    private val viewModel by viewModels<MeteorViewModel>()
    private var savedFilterID = 0
    private lateinit var binding: FragmentMeteorBinding
    private val adapter: MeteorAdapter by lazy {
        MeteorAdapter(this, false)
    }

    private val layoutManager = LinearLayoutManagerWithSmoothScroller(activity)

    private var listMeteors = ArrayList<Meteor>()

    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_meteor, container, false
        )
        val view: View = binding.root
        setupViewModel()
        binding.viewModel = viewModel
        binding.meteorAdapter = adapter
        binding.fabFilter.setOnClickListener {
            showBottomSheetDialog()
        }
        binding.swipeLayout.setOnRefreshListener(this)
        setupUi()
        return view
    }

    private fun setupViewModel() {
        /* viewModel = ViewModelProvider(
             this,
             ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
         ).get(
             MeteorViewModel::class.java
         )
         viewModel.context = activity as FragmentActivity*/
    }

    private fun filterMeteor(filterParameter: Int) {
        listMeteors.clear()
        listMeteors.addAll(viewModel.filterMeteors(filterParameter))
        adapter.setMeteorList(listMeteors)
        adapter.notifyDataSetChanged()
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(activity as FragmentActivity)
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_filter)
        val radioGroupFilter = bottomSheetDialog.findViewById<RadioGroup>(R.id.radioGroupFilter)
        val appCompatButtonApply = bottomSheetDialog.findViewById<AppCompatButton>(R.id.buttonApply)
        if (savedFilterID != 0) {
            radioGroupFilter?.check(savedFilterID)
        }
        var filterType = 0
        radioGroupFilter?.setOnCheckedChangeListener { _, checkedId ->
            savedFilterID = checkedId
            filterType = when (checkedId) {
                R.id.radioButtonWeight -> MeteorViewModel.FILTER_BY_WEIGHT
                R.id.radioButtonWeightDesc -> MeteorViewModel.FILTER_BY_WEIGHT_DESC
                R.id.radioButtonLocation -> MeteorViewModel.FILTER_BY_LOCATION
                R.id.radioButtonYear -> MeteorViewModel.FILTER_BY_YEAR
                R.id.radioButtonYearDesc -> MeteorViewModel.FILTER_BY_YEAR_DESC
                R.id.radioButtonFall -> MeteorViewModel.FILTER_BY_FALL
                R.id.radioButtonName -> MeteorViewModel.FILTER_BY_NAME
                else -> 0
            }
        }
        appCompatButtonApply?.setOnClickListener {
            filterMeteor(filterType)
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.show()
    }

    override fun onMeteorMapClick(meteor: Meteor) {
        if (CommonUtils.checkPermission(activity as FragmentActivity)) {
            if (meteor.reclat != null && meteor.reclong != null) {
                MeteorMapDialogFragment.newInstance(meteor).show(childFragmentManager, "TAG")
            } else {
                Toast.makeText(activity, "Lattitude and Longitude not found", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(activity, "Permission Not granted", Toast.LENGTH_SHORT).show()
            (activity as MeteorActivity).requestPermission()
        }
    }

    override fun onFevoriteClick(meteor: Meteor) {
        viewModel.insertFavoriteMeteors(meteor)
        meteor.isFavorite = if (meteor.isFavorite > 0) 1 else 0
        adapter.notifyItemChanged(meteor.position, meteor)
    }

    override fun onRefresh() {
        savedFilterID = 0
        getMeteors()
    }

    private fun setupUi() {
        binding.recyclerViewMeteor.layoutManager = layoutManager
        initScrollListener()
        if (NetworkUtil.isAvailable(activity as FragmentActivity)) {
            getMeteors()
        } else {
            (activity as MeteorActivity).showMessageOKCancel(
                "No Internet Connection"
            )
            { dialog, which ->
                (activity as MeteorActivity).finish()
            }
        }
    }

    private fun loadMore() {
        viewModel.fetchMeteorsByPage().observe(viewLifecycleOwner, {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        adapter.setMeteorList(resource.data as ArrayList<Meteor>)
                        adapter.notifyDataSetChanged()
                        isLoading = false
                        binding.swipeLayout.isRefreshing = false
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            activity as FragmentActivity,
                            "Error ${resource.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {
                        // now implemented using daabinding
                    }
                }
            }
        })
    }

    private fun getMeteors() {
        viewModel.getMeteors().observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loadMore()
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            activity as FragmentActivity,
                            "Error ${resource.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    Status.LOADING -> {
                        // now implemented using daabinding
                    }
                }
            }
        })
    }

    private fun initScrollListener() {
        binding.recyclerViewMeteor.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() >= adapter.itemCount - 2) {
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }
}