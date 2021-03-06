package br.com.angelorobson.application.ui.partials.virusstatus.virusstatusbrazil

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import br.com.angelorobson.application.ui.activities.AboutActivity
import br.com.angelorobson.application.util.BindingFragment
import br.com.angelorobson.application.util.EventObserver
import br.com.angelorobson.covid19.R
import br.com.angelorobson.covid19.databinding.FragmentVirusStatusBrazilBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class VirusStatusBrazilFragment : BindingFragment<FragmentVirusStatusBrazilBinding>() {

    private val viewModel by viewModel<VirusStatusBrazilViewModel>()

    override fun getLayoutResId(): Int = R.layout.fragment_virus_status_brazil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setUpFragment()
        initObservers()
    }

    private fun setUpFragment() {
        showToolbarWithoutDisplayArrowBack(getString(R.string.recent))
        viewModel.getStatusVirusBrazil()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getStatusVirusBrazil()
        }
    }

    private fun initObservers() {
        viewModel.successObserver.observe(viewLifecycleOwner, EventObserver {
            binding.virusBrazil = it
        })

        viewModel.errorObserver.observe(viewLifecycleOwner, EventObserver {

        })

        viewModel.emptyObserver.observe(viewLifecycleOwner, EventObserver {

        })

        viewModel.isLoadingEventObserver.observe(viewLifecycleOwner, EventObserver {
            binding.swipeRefreshLayout.isRefreshing = it
            binding.tvUpdateAt.visibility = if (it) View.INVISIBLE else View.VISIBLE
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.about_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                startActivity(Intent(requireContext(), AboutActivity::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }

}

