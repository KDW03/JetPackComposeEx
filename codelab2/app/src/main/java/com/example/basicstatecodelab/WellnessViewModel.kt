package com.example.basicstatecodelab

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {
    //_tasks 변수를 정의하고 tasks를 목록으로 노출하여 ViewModel 외부에서 수정할 수 없도록
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask> = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: WellnessTask, checked: Boolean) =
        tasks.find { item.id == it.id }?.let { task ->
            task.checked = checked
        }
}


private fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }