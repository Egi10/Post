package id.buaja.post.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.domain.model.Post
import id.buaja.post.MyApp
import id.buaja.post.R
import id.buaja.post.base.BaseFragment
import id.buaja.post.databinding.FragmentSearchPostBinding
import id.buaja.post.di.viewmodel.ViewModelFactory
import id.buaja.post.ui.home.HomeAdapter
import id.buaja.post.utils.toast
import javax.inject.Inject

class SearchPostFragment : BaseFragment(R.layout.fragment_search_post) {
    private var _binding: FragmentSearchPostBinding? = null
    private val binding get() = _binding!!

    private val list: MutableList<Post> = mutableListOf()
    private var homeAdapter: HomeAdapter? = null

    @Inject
    lateinit var factory: ViewModelFactory

    private val searchPostViewModel by viewModels<SearchPostViewModel> {
        factory
    }

    override fun initObservable() {
        with(searchPostViewModel) {
            success.observe(this@SearchPostFragment, {
                list.clear()
                list.addAll(it)
                homeAdapter?.notifyDataSetChanged()
            })

            empty.observe(this@SearchPostFragment, {
                list.clear()
                toast(it)
            })
        }
    }

    override fun initView(view: View) {
        homeAdapter = HomeAdapter(list) {

        }

        binding.apply {
            tilSearch.setEndIconOnClickListener {
                searchPostViewModel.getPostByTitle(tieSearch.text.toString())
            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            recyclerView.adapter = homeAdapter
        }
    }

    override fun createView(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = FragmentSearchPostBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun destroyView() {
        _binding = null
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }
}