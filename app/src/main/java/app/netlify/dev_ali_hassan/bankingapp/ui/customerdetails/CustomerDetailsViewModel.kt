package app.netlify.dev_ali_hassan.bankingapp.ui.customerdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CustomerDetailsViewModel: ViewModel() {

    private val eventsChannel = Channel<CustomerDetailsEvents>()
    val eventsFlow = eventsChannel.receiveAsFlow()

    fun userClickTransferMoneyBtn() =
        viewModelScope.launch {
            eventsChannel.send(CustomerDetailsEvents.DisplayAmountMoneyDialog)
        }

    sealed class CustomerDetailsEvents{
        object DisplayAmountMoneyDialog: CustomerDetailsEvents()
    }
}