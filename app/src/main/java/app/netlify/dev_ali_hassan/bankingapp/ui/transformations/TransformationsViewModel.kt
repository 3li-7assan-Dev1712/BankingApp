package app.netlify.dev_ali_hassan.bankingapp.ui.transformations

import androidx.lifecycle.ViewModel
import app.netlify.dev_ali_hassan.bankingapp.data.daos.TransformationsDao
import app.netlify.dev_ali_hassan.bankingapp.data.models.Transformation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransformationsViewModel @Inject constructor(
    transformationsDao: TransformationsDao
) : ViewModel() {


    val tranformationsFlow = transformationsDao.getAllTransformations()

    fun provideTempData() = listOf(
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true),
        Transformation("Esam",  System.currentTimeMillis(), 342, true)

    )
}