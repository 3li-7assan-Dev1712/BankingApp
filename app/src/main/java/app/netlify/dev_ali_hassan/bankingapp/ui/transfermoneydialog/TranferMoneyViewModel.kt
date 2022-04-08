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

@HiltViewModel
class TranferMoneyViewModel @Inject constructor(
    val customerDao: CustomerDao,
    val transformationsDao: TransformationsDao
) : ViewModel() {

    val eventsChannel = Channel<TransferMoneyEvents>()
    val eventsFlow = eventsChannel.receiveAsFlow()

    fun userTransferMoneyToOtherCustomer(amount: Int, customer: Customer) {
        val availableBalance = customer.customerBankAmount + amount
        val updatedCustomer = customer.copy(customerBankAmount = availableBalance)
        viewModelScope.launch {
            customerDao.updateCustomer(updatedCustomer)
        }
        val newTrans = Transformation(customer.customerName, balance = amount, isReceived = false)
        createNewTransformation(newTrans)
        notifyFragmentOfSuccessfullOperation()
    }

    private fun notifyFragmentOfSuccessfullOperation() =
        viewModelScope.launch {
            eventsChannel.send(TransferMoneyEvents.OperationFinishedSuccessfully)
        }

    fun createNewTransformation(transformation: Transformation) {
        viewModelScope.launch {
            transformationsDao.addNewTransformation(transformation)
        }
    }


    sealed class TransferMoneyEvents {
        object OperationFinishedSuccessfully : TransferMoneyEvents()
    }
}