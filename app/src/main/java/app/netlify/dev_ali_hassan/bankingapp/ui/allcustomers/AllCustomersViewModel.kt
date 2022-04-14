package app.netlify.dev_ali_hassan.bankingapp.ui.allcustomers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.netlify.dev_ali_hassan.bankingapp.data.daos.CustomerDao
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model will be responsible for the business logic of the all customers fragment. It will interact
 * with the database, and take all the back end side.
 */
@HiltViewModel
class AllCustomersViewModel @Inject constructor(
    customerDao: CustomerDao
) : ViewModel() {

    // to give orders to the fragment we need the coroutines channel which give us the ability
    // to send events that we can receive it as a flow and collect it in the fragment : )
    private val eventsChannel = Channel<AllCustomerEvents>()
    // receive the channel as a flow to be able to collect it in the fragment. *_-
    val eventsFlow = eventsChannel.receiveAsFlow()

    // all customers fromt the Room database.
    val customersFlow = customerDao.getAllCustomers()

    fun userSelectCustomer(customer: Customer) =
        viewModelScope.launch {
            eventsChannel.send(AllCustomerEvents.NavigateToDetailsFragment(customer))
        }


    sealed class AllCustomerEvents {
        data class NavigateToDetailsFragment(val customer: Customer): AllCustomerEvents()
    }

}