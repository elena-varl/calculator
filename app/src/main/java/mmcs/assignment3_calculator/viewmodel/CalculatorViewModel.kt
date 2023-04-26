package mmcs.assignment3_calculator.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField

class CalculatorViewModel : BaseObservable(), Calculator {
    override var display = ObservableField<String>()
    private var first = ""
    private var second = ""
    private var op: Operation? = null

    override fun addDigit(dig: Int) {
        if (op == null)
            first += dig.toString()
        else {
            second += dig.toString()
        }
        Update()
    }

    override fun addPoint() {
        //if (op != null) {
            if (!first.contains('.'))
                first += "."
            if (!second.contains('.'))
                second += "."
       // }
        Update()
    }

    override fun addOperation(oper: Operation) {
        if (first.isNotEmpty() && second.isNotEmpty()) {
            compute()
            Update()
        }
        if (first.isNotEmpty()) {
            op = oper
        }


    }

    override fun compute() {
        if (first.isEmpty() || second.isEmpty() || op == null)
            return
        val f = first.toDouble()
        val s = second.toDouble()
        var result = 0.0
        when (op) {
            Operation.ADD -> result = f + s
            Operation.MUL -> result = f * s
            Operation.DIV -> result = f / s
            Operation.SUB -> result = f - s
        }
        first = ChangeFormat(result)
        second = ""
        op = null
        Update()

    }

    private fun ChangeFormat(x: Double): String {
        if (x - x.toInt() == 0.0)
            return x.toInt().toString()
        val s = "%.6f".format(x)
        return s.toString()
    }

    override fun clear() {
        if (second.isNotEmpty()) {
            second = ""
            Update()
            return
        }
        if (op != null) {
            op = null
            Update()
            return
        }
        first = ""
        Update()
    }

    override fun reset() {
        first = ""
        second = ""
        op = null
        Update()
    }

    private fun Update() {
        var res = first
        if (op != null) {
            when(op.toString())
            {
                "SUB"->res+="-"
                "ADD"->res+="+"
                "DIV"->res+="/"
                "MUL"->res+="*"
            }

            res += " $second"
        }
        display.set(res)
    }

    override fun back() {
        when {
            op == null -> {
                first = first.substring(0, kotlin.math.max(0, first.length.minus(1)))
            }
            second != "" -> {
                second = second.substring(0, kotlin.math.max(0, second.length.minus(1)))
            }
            else -> {
                op = null
            }
        }
        Update()
    }

}