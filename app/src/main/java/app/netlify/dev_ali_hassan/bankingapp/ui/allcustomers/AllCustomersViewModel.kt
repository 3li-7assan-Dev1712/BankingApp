package app.netlify.dev_ali_hassan.bankingapp.ui.allcustomers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.data.models.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model will be responsible for the business logic of the all customers fragment. I will interact
 * with the database, and take all the back end side.
 */
@HiltViewModel
class AllCustomersViewModel @Inject constructor() : ViewModel() {


    private val eventsChannel = Channel<AllCustomerEvents>()
    val eventsFlow = eventsChannel.receiveAsFlow()


    fun userSelectCustomer(customer: Customer) =
        viewModelScope.launch {
            eventsChannel.send(AllCustomerEvents.NavigateToDetailsFragment(customer))
        }


    sealed class AllCustomerEvents {
        data class NavigateToDetailsFragment(val customer: Customer): AllCustomerEvents()
    }



    fun provideTempData() = listOf(
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE),
        Customer("Ali Hassan", "38KDF938IOA", 600, "ali@gmail.com", 3, Gender.MALE)
    )
}