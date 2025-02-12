import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_app_assignment.R
import com.example.student_app_assignment.listeners.OnStudentClickListener
import com.example.student_app_assignment.models.Student

class StudentAdapter(
    private val students: List<Student>,
    private val listener: OnStudentClickListener
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(student: Student) {
            itemView.findViewById<TextView>(R.id.studentName).text = student.name
            itemView.findViewById<TextView>(R.id.studentId).text = student.id
            itemView.findViewById<CheckBox>(R.id.studentCheckBox).isChecked = student.isChecked
            itemView.findViewById<ImageView>(R.id.studentImage).setImageResource(R.drawable.student_img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])

        holder.itemView.setOnClickListener {
            listener.onStudentClick(students[position].uuid)
        }

    }
    override fun getItemCount(): Int = students.size

}