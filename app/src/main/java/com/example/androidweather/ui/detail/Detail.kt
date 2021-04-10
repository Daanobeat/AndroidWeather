package com.example.androidweather.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.navArgs
import com.example.androidweather.R
import com.example.androidweather.data.model.Item
import com.example.androidweather.databinding.FragmentDetailBinding
import com.example.androidweather.util.Extensions.parseSecondsToDate

class Detail : Fragment(R.layout.fragment_detail) {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private val adapter = DeatailAdapter()
    val args:DetailArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvDetail.adapter = adapter
        viewModel.setList(args.ITEM)
        viewModel.items.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }
}

class DetailViewModel : ViewModel() {

    private var _items = MutableLiveData<List<Pair<String, String>>>()
    val items: LiveData<List<Pair<String, String>>> get() = _items

    fun setList(item: Item) {
        _items.value = listOf(
            Pair("Datum", item.main.temp.toString()),
            Pair("Temperatur", item.dt.parseSecondsToDate())
        )
    }
}