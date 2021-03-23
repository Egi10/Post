package id.buaja.post.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.domain.model.Post
import id.buaja.post.MyApp
import id.buaja.post.R
import id.buaja.post.base.BaseFragment
import id.buaja.post.databinding.FragmentHomeBinding
import id.buaja.post.di.viewmodel.ViewModelFactory
import javax.inject.Inject

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    @Inject
    lateinit var factory: ViewModelFactory

    private val homeViewModel by viewModels<HomeViewModel> {
        factory
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val list: MutableList<Post> = mutableListOf()
    private var homeAdapter: HomeAdapter? = null

    override fun initObservable() {
        with(homeViewModel) {
            loading.observe(this@HomeFragment, {
                binding.swipe.isRefreshing = it
            })

            success.observe(this@HomeFragment, {
                list.clear()
                list.addAll(it)
                homeAdapter?.notifyDataSetChanged()
            })

            errorMessage.observe(this@HomeFragment, {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
        }
    }

    override fun initView(view: View) {
        homeAdapter = HomeAdapter(list) {

        }

        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.adapter = homeAdapter

            swipe.setOnRefreshListener {
                homeViewModel.getPost()
            }
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun destroyView() {
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }
}