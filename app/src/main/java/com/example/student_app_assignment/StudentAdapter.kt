import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_app_assignment.R
import com.example.studentsapp.Student

class StudentAdapter(
    private val students: List<Student>,
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    // ViewHolder class to hold references to the views for each item
    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val studentImage: ImageView = itemView.findViewById(R.id.studentImage)
        val studentName: TextView = itemView.findViewById(R.id.studentName)
        val studentId: TextView = itemView.findViewById(R.id.studentId)
        val studentCheckBox: CheckBox = itemView.findViewById(R.id.studentCheckBox)

        fun bind(student: Student) {
            studentName.text = student.name
            studentId.text = student.id
            studentCheckBox.isChecked = student.isChecked
            studentImage.setImageResource(R.drawable.studnet) // Replace with your static image resource
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }
    override fun getItemCount(): Int = students.size

}