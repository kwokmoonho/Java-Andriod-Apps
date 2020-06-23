package CodeKing.i_am_thankful_2.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import CodeKing.i_am_thankful_2.Controller.Note;
import CodeKing.i_am_thankful_2.R;

/**
 *
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener listener;

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    /**
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewItem1.setText(currentNote.getTxt1());
        //since the days is a interger, we have to cast it to string first
        holder.textViewDays.setText(String.valueOf(currentNote.getDays()));
    }

    /**
     * @return the number of days
     */
    @Override
    public int getItemCount() {
        return notes.size();

    }

    /**
     * @param notes
     */
    public void setNotes(List<Note> notes){
        this.notes = notes;
        notifyDataSetChanged();
    }

    /**
     * @param position
     * @return
     */
    //get the notes from the adapter from the outside classes
    public Note getNotePosition(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewItem1;
        private TextView textViewDays;

        /**
         * @param itemView
         */
        public NoteHolder(View itemView) {
            super(itemView);
            textViewItem1 = itemView.findViewById(R.id.txt1);
            textViewDays = itemView.findViewById(R.id.days);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(position));
                    }
                }
            });
        }
    }

    /**
     *
     */
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener (OnItemClickListener listener) {
        this.listener = listener;
    }
}
