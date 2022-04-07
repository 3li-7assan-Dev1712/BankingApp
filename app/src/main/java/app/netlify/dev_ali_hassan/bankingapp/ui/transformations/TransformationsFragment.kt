package app.netlify.dev_ali_hassan.bankingapp.ui.transformations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import app.netlify.dev_ali_hassan.bankingapp.R
import app.netlify.dev_ali_hassan.bankingapp.databinding.TranformationsFragmentBinding

class TransformationsFragment: Fragment(R.layout.tranformations_fragment) {

    private lateinit var binding: TranformationsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = TranformationsFragmentBinding.bind(view)

    }

}