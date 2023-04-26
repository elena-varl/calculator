package mmcs.assignment3_calculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.databinding.DataBindingUtil
import mmcs.assignment3_calculator.R
import mmcs.assignment3_calculator.databinding.ActivityMainBinding
import mmcs.assignment3_calculator.viewmodel.Calculator
import mmcs.assignment3_calculator.viewmodel.CalculatorViewModel
import mmcs.assignment3_calculator.viewmodel.Operation

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var viewModel: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = CalculatorViewModel()

        mainBinding.viewModel = viewModel
    }
    fun allClearAction(view: View) {
        viewModel.reset()
    }
    fun backSpaceAction(view: View) {
        viewModel.back()
    }
    fun clearAction(view: View) {viewModel.clear()}
    fun equalsAction(view: View) { viewModel.compute() }
    fun numberAction(view: View) {
        if ((view as Button).text == ".")
            viewModel.addPoint()
        else
            viewModel.addDigit((view as Button).text.toString().toInt())}
    fun operationAction(view: View) {
        when((view as Button).text) {
            "+" -> viewModel.addOperation(Operation.ADD)
            "/" -> viewModel.addOperation(Operation.DIV)
            "-" -> viewModel.addOperation(Operation.SUB)
            "x" -> viewModel.addOperation(Operation.MUL)
        }
    }
}