package app.netlify.dev_ali_hassan.bankingapp.ui.transformations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.databinding.TranformationsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

/**
 * The is the fragment that will display transformations in the screen. The data will come
 * from the Room database using a dedicated view model to do that on a background thread
 * with coroutines.
 */
@AndroidEntryPoint
class TransformationsFragment: Fragment(R.layout.tranformations_fragment) {

    // the view model of the current fragment to handle all business logic.
    private val viewModel: TransformationsViewModel by viewModels()

    // viewBinding
    private lateinit var binding: TranformationsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TranformationsFragmentBinding.bind(view)

        val adapter = TransformationsAdapter(requireContext())
        binding.transformationsRv.adapter = adapter



        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tranformationsFlow.collect {transformations ->
                binding.progressBar.visibility = View.INVISIBLE
                adapter.submitList(transformations)
            }
        }

    }

}