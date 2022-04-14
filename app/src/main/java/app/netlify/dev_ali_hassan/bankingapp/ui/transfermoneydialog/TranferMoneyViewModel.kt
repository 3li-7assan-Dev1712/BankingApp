package app.netlify.dev_ali_hassan.bankingapp.ui.transfermoneydialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.netlify.dev_ali_hassan.bankingapp.data.daos.CustomerDao
import app.netlify.dev_ali_hassan.bankingapp.data.daos.TransformationsDao
import app.netlify.dev_ali_hassan.bankingapp.data.models.Customer
import app.netlify.dev_ali_hassan.bankingapp.data.models.Transformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The view model will do all the business logic to its fragment like interacting with internet
 * or the database and gives orders to the fragment.
 */
@HiltViewModel
class TranferMoneyViewModel @Inject constructor(
    val customerDao: CustomerDao,
    val transformationsDao: TransformationsDao
) : ViewModel() {

    // to give orders to the fragment we need the coroutines channel which give us the ability
    // to send events that we can receive it as a flow and collect it in the fragment : )
    val eventsChannel = Channel<TransferMoneyEvents>()
    // receive the channel as a flow to be able to collect it in the fragment. *_-
    val eventsFlow = eventsChannel.receiveAsFlow()

    fun userTransferMoneyToOtherCustomer(amount: Int, customer: Customer) {
        val availableBalance = customer.customerBankAmount + amount
        val updatedCustomer = customer.copy(customerBankAmount = availableBalance)
        viewModelScope.launch {
            customerDao.updateCustomer(updatedCustomer)
        }
        val newTrans = Transformation(customer.customerName, balance = amount, isReceived = false)
        createNewTransformation(newTrans)
        notifyFragmentOfSuccessfullOperation(updatedCustomer.customerBankAmount)
    }

    private fun notifyFragmentOfSuccessfullOperation(updatedBankBalance: Int) =
        viewModelScope.launch {
            eventsChannel.send(TransferMoneyEvents.OperationFinishedSuccessfully(updatedBankBalance))
        }

    fun createNewTransformation(transformation: Transformation) {
        viewModelScope.launch {
            transformationsDao.addNewTransformation(transformation)
        }
    }


    sealed class TransferMoneyEvents {
        data class OperationFinishedSuccessfully(val updatedBalance: Int) : TransferMoneyEvents()
    }
}