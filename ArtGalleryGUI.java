import javax.swing.*;
import java.awt.*;
import java.util.*;

// ================= INTERFACE =================
interface Manageable {
    void add();
    void view();
    void update();
    void delete();
}

// ================= ABSTRACT CLASS =================
abstract class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }
}

// ================= ARTIST CLASS =================
class Artist extends Person {
    private String specialty;
    private String contact;

    public Artist(String id, String name, String specialty, String contact) {
        super(id, name);
        this.specialty = specialty;
        this.contact = contact;
    }

    public String toString() {
        return "ID: " + id +
                "\nName: " + name +
                "\nSpecialty: " + specialty +
                "\nContact: " + contact +
                "\n---------------------\n";
    }
}

// ================= ARTIST MANAGER =================
class ArtistManager {
    private ArrayList<Artist> artists = new ArrayList<>();

    public void addArtist(String id, String name, String specialty, String contact) {
        artists.add(new Artist(id, name, specialty, contact));
    }

    public String viewArtists() {
        if (artists.isEmpty()) return "No artists found!\n";

        StringBuilder sb = new StringBuilder();
        for (Artist a : artists) {
            sb.append(a.toString());
        }
        return sb.toString();
    }
}

// ================= MAIN GUI CLASS =================
public class Main extends JFrame {

    ArtistManager manager = new ArtistManager();

    JTextField idField, nameField, specialtyField, contactField;
    JTextArea output;

    public void ArtGalleryGUI() {

        setTitle("Online Art Gallery System");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ---------- FORM ----------
        JPanel form = new JPanel(new GridLayout(4, 2));

        form.add(new JLabel("Artist ID"));
        idField = new JTextField();
        form.add(idField);

        form.add(new JLabel("Name"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Specialty"));
        specialtyField = new JTextField();
        form.add(specialtyField);

        form.add(new JLabel("Contact"));
        contactField = new JTextField();
        form.add(contactField);

        add(form, BorderLayout.NORTH);

        // ---------- BUTTONS ----------
        JPanel buttons = new JPanel();

        JButton addBtn = new JButton("Add Artist");
        JButton viewBtn = new JButton("View Artists");

        buttons.add(addBtn);
        buttons.add(viewBtn);

        add(buttons, BorderLayout.CENTER);

        // ---------- OUTPUT ----------
        output = new JTextArea();
        output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.SOUTH);

        // ---------- ACTIONS ----------
        addBtn.addActionListener(e -> {
            manager.addArtist(
                    idField.getText(),
                    nameField.getText(),
                    specialtyField.getText(),
                    contactField.getText()
            );
            JOptionPane.showMessageDialog(this, "Artist Added Successfully");
            clearFields();
        });

        viewBtn.addActionListener(e -> {
            output.setText(manager.viewArtists());
        });

        setVisible(true);
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        specialtyField.setText("");
        contactField.setText("");
    }

    public static void main(String[] args) {
         Main m=new Main();
         m.ArtGalleryGUI();
    }
}
