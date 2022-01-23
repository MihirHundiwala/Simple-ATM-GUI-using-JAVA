import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class RegisterScreen extends JFrame {
	private JPanel contentPane;
	private JTextField first_name;
	private JPasswordField pin;
	private JTextField last_name;

	/**
	 * Create the frame.
	 */
	protected RegisterScreen() {
		setTitle("Register");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 292, 469);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("A T M");
		lblNewLabel.setBorder(new MatteBorder(3, 1, 3, 1, (Color) new Color(153, 0, 255)));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 25));
		lblNewLabel.setBackground(new Color(153, 102, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 276, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("First name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 73, 125, 27);
		contentPane.add(lblNewLabel_1);
		
		first_name = new JTextField();
		first_name.setHorizontalAlignment(SwingConstants.CENTER);
		first_name.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		first_name.setBorder(new LineBorder(new Color(51, 0, 102), 2, true));
		first_name.setBounds(20, 100, 192, 32);
		contentPane.add(first_name);
		first_name.setColumns(10);
		
		
		JLabel lblNewLabel_2 = new JLabel("Last name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(20, 156, 125, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PIN");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel_3.setBounds(20, 243, 125, 27);
		contentPane.add(lblNewLabel_3);
		
		JButton register = new JButton("Register");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// REGISTER NEW ACCOUNT (duplicate names allowed but not account_id)
				try 
					{	
						String n=first_name.getText() +" "+ last_name.getText();
						String pass=pin.getText();
						// RANDOM FIGURE FOR ACCOUNT_ID
						Random rand =new Random();
						int id=rand.nextInt(1000000);
						System.out.println(n+" "+id+" "+pass);
						//CALL 	createaccount() FOR NEW ACCOUNT 
						Database.createaccount(n,id,pass,0,"");
						JOptionPane.showMessageDialog(register,"Registration Successful.\nYour Account ID : "+id);
						new LoginScreen().setVisible(true);
						RegisterScreen.this.dispose();
					}
				catch(Exception e1)
					{
						JOptionPane.showMessageDialog(register,"Invalid Entry.","Inane warning",JOptionPane.WARNING_MESSAGE);
					}		
			}
		});
		register.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(153, 0, 255)));
		register.setBounds(20, 357, 100, 27);
		contentPane.add(register);
		
		pin = new JPasswordField();
		pin.setHorizontalAlignment(SwingConstants.CENTER);
		pin.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		pin.setBorder(new LineBorder(new Color(51, 0, 153), 2));
		pin.setToolTipText("4 digit pin, Only numbers are allowed");
	
		pin.setBounds(20, 279, 192, 32);
		contentPane.add(pin);
		
		last_name = new JTextField();
		last_name.setHorizontalAlignment(SwingConstants.CENTER);
		last_name.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		last_name.setBorder(new LineBorder(new Color(51, 0, 102), 2));
		last_name.setBounds(20, 199, 192, 33);
		contentPane.add(last_name);
		last_name.setColumns(10);
		
		JButton login = new JButton("Login");
		login.addMouseListener(new MouseAdapter() {
			@Override
			// GO TO LOGIN PAGE 
			public void mouseClicked(MouseEvent e) {
				RegisterScreen.this.dispose();
				new LoginScreen().setVisible(true);
			}
		});
		login.setBorder(new MatteBorder(3, 3, 3, 3, (Color) new Color(153, 0, 255)));
		login.setBounds(20, 395, 100, 23);
		contentPane.add(login);
	}
}
