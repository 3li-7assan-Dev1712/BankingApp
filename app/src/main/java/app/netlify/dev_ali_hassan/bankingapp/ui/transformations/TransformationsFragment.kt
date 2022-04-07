package app.netlify.dev_ali_hassan.bankingapp.ui.transformations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.databinding.TranformationsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransformationsFragment: Fragment(R.layout.tranformations_fragment) {

    private val viewModel: TransformationsViewModel by viewModels()

    private lateinit var binding: TranformationsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TranformationsFragmentBinding.bind(view)

        val adapter = TransformationsAdapter(requireContext())
        binding.transformationsRv.adapter = adapter
        adapter.submitList(viewModel.provideTempData())

    }

}