import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class LoginScreen extends JFrame {
	
	private JPanel contentPane;
	private JTextField account_no;
	private JPasswordField pin;

	/**
	 * Create the frame.
	 */
	protected LoginScreen() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 277, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("A T M");
		lblNewLabel.setBorder(new MatteBorder(3, 0, 3, 0, (Color) new Color(153, 0, 255)));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Algerian", Font.BOLD, 25));
		lblNewLabel.setBackground(new Color(153, 102, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 267, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Account Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(10, 78, 174, 22);
		contentPane.add(lblNewLabel_1);
		
		account_no = new JTextField();
		account_no.setToolTipText("Enter 6 digit Account ID");
		account_no.setHorizontalAlignment(SwingConstants.CENTER);
		account_no.setBorder(new LineBorder(new Color(51, 0, 102), 2, true));
		account_no.setBounds(20, 111, 219, 40);
		contentPane.add(account_no);
		account_no.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Enter PIN");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(10, 174, 146, 22);
		contentPane.add(lblNewLabel_2);
		
		pin = new JPasswordField();
		pin.setToolTipText("Enter Your 4 digit Pin");
		pin.setHorizontalAlignment(SwingConstants.CENTER);
		pin.setBorder(new LineBorder(new Color(51, 0, 102), 2, true));
		pin.setBounds(20, 207, 219, 40);
		contentPane.add(pin);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FOLLOWING STEPS WILL BE PERFORMED WHEN CLICKED ON 'Login'.
				try 
				{
					int id=Integer.valueOf(account_no.getText());
					String p=pin.getText();
					// Authenticate account number and pin entered by user.
					boolean b=new Authenticate().authenticate(id,p);
					if(b)
					{
						//POP UP FOR SUCCESSFUL LOGIN.
						JOptionPane.showMessageDialog(login,"Login Successful.\nWelcome to ATM!");
						LoginScreen.this.dispose();
						
						// Get account holder name through account ID
						String name=Database.getnamethroughaccountID(id);
						Transaction T =new Transaction();
						T.account_ID=id;
						T.setVisible(true);
						T.welcomebar.setText("Welcome "+name+" !");
					}
					else
					{
						//POP UP FOR WRONG  ACCOUNT NUMBER OR PASSWORD
						JOptionPane.showMessageDialog(login,"Invalid Account Number or PIN.","Inane warning",JOptionPane.WARNING_MESSAGE);
					}
				}
				catch(Exception e1)
				{
					// FOR INVALID ENTRIES FOR ACCOUNT_ID AND PASSWORD
					System.out.println(e1+" Here");
					JOptionPane.showMessageDialog(login,"Invalid Entry.","Inane warning",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		login.setFont(new Font("Tahoma", Font.BOLD, 13));
		login.setBorder(new LineBorder(new Color(51, 0, 102), 2));
		login.setBounds(75, 283, 107, 23);
		contentPane.add(login);
		
		JButton btnNewButton_1 = new JButton("Create account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			// FOLLOWING STEPS WILL BE PERFORMED WHEN CLICKED ON 'Create Account'.
				new RegisterScreen().setVisible(true);
				LoginScreen.this.dispose();
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBorder(new LineBorder(new Color(51, 0, 102), 2));
		btnNewButton_1.setBounds(75, 334, 107, 23);
		contentPane.add(btnNewButton_1);

	}
}
