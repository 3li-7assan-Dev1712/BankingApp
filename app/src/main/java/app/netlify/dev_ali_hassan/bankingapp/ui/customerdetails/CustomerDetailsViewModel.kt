package app.netlify.dev_ali_hassan.bankingapp.ui.customerdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * This view model will be responsible for the business logic of the all customers fragment. It will interact
 * with the database, and take all the back end side.
 */
@HiltViewModel
class CustomerDetailsViewModel @Inject constructor(): ViewModel() {

    // to give orders to the fragment we need the coroutines channel which give us the ability
    // to send events that we can receive it as a flow and collect it in the fragment : )
    private val eventsChannel = Channel<CustomerDetailsEvents>()
    // receive the channel as a flow to be able to collect it in the fragment. *_-
    val eventsFlow = eventsChannel.receiveAsFlow()

    fun userClickTransferMoneyBtn() =
        viewModelScope.launch {
            eventsChannel.send(CustomerDetailsEvents.DisplayAmountMoneyDialog)
        }

    sealed class CustomerDetailsEvents{
        object DisplayAmountMoneyDialog: CustomerDetailsEvents()
    }
}