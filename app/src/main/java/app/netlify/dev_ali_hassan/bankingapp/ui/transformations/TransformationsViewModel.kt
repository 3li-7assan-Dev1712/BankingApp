package app.netlify.dev_ali_hassan.bankingapp.ui.transformations

import androidx.lifecycle.ViewModel
import app.netlify.dev_ali_hassan.bankingapp.data.models.Transformation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransformationsViewModel @Inject constructor() : ViewModel() {


    fun provideTempData() = listOf(
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true),
        Transformation("Esam", 2, "3938DFEG8", 342, true)
    )
}