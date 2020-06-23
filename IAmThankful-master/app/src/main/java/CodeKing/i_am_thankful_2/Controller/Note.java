package CodeKing.i_am_thankful_2.Controller;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(tableName = "note_table") // tableName
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int days;
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    private String txt5;

    /**
     * @param days
     * @param txt1
     * @param txt2
     * @param txt3
     * @param txt4
     * @param txt5
     */
    //non-default constructor
    public Note(int days, String txt1, String txt2, String txt3, String txt4, String txt5) {
        this.days = days;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
        this.txt4 = txt4;
        this.txt5 = txt5;
    }

    /**
     * @param id
     */
    //only set the id because this is not set in the constructor
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the days
     */
    public int getDays() {
        return days;
    }

    /**
     * @return the txt1
     */
    public String getTxt1() {
        return txt1;
    }

    /**
     * @return the txt2
     */
    public String getTxt2() {
        return txt2;
    }

    /**
     * @return txt3
     */
    public String getTxt3() {
        return txt3;
    }

    /**
     * @return txt4
     */
    public String getTxt4() {
        return txt4;
    }

    /**
     * @return txt5
     */
    public String getTxt5() {
        return txt5;
    }
}

