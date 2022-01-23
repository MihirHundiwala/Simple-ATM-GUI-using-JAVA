import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.TextArea;

public class Transaction extends JFrame {

	private JPanel contentPane;
	private JTextField deposit= new JTextField();
	private JTextField withdrawal= new JTextField();
	private JLabel balance = new JLabel("");
	private TextArea textArea;
	protected int account_ID;
	protected JLabel welcomebar=new JLabel();

	/**
	 * Create the frame.
	 */
	protected Transaction() {
		setTitle("Transaction Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 491);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 153, 255));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double amount=Double.valueOf(deposit.getText());
					Account.deposit(account_ID,amount);
					deposit.setText("");
					JOptionPane.showMessageDialog(btnDeposit,"Transaction Successful");
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(btnDeposit,"Invalid Entry.","Inane warning",JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnDeposit.setBackground(Color.WHITE);
		btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnDeposit.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		btnDeposit.setBounds(75, 105, 119, 40);
		contentPane.add(btnDeposit);
		
		JButton btnWithdrawal = new JButton("Withdrawal");
		btnWithdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double amount=Double.valueOf(withdrawal.getText());
					withdrawal.setText("");
					boolean b=Account.withdraw(account_ID,amount);
					if(b)
						JOptionPane.showMessageDialog(btnWithdrawal,"Transaction Successful");
					else
						JOptionPane.showMessageDialog(btnWithdrawal,"Insufficient Funds");
				} 
				catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(btnWithdrawal,"Invalid Entry.","Inane warning",JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnWithdrawal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnWithdrawal.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		btnWithdrawal.setBackground(Color.WHITE);
		btnWithdrawal.setBounds(75, 216, 119, 40);
		contentPane.add(btnWithdrawal);
		
		JButton btnBalanceEnquiry = new JButton("Balance Enquiry");
		btnBalanceEnquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					double b=Account.balance_enquiry(account_ID);
					balance.setText(String.valueOf(b));
				} catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(btnWithdrawal,"Error","Inane warning",JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnBalanceEnquiry.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBalanceEnquiry.setBorder(new LineBorder(Color.DARK_GRAY, 2));
		btnBalanceEnquiry.setBackground(Color.WHITE);
		btnBalanceEnquiry.setBounds(49, 286, 186, 40);
		contentPane.add(btnBalanceEnquiry);
		
		deposit = new JTextField();
		deposit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		deposit.setForeground(new Color(0, 204, 0));
		deposit.setHorizontalAlignment(SwingConstants.CENTER);
		deposit.setBorder(new LineBorder(new Color(0, 0, 0)));
		deposit.setBounds(36, 63, 199, 31);
		contentPane.add(deposit);
		deposit.setColumns(10);
		withdrawal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		withdrawal.setForeground(Color.RED);
		withdrawal.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawal.setBorder(new LineBorder(Color.BLACK));
		withdrawal.setBounds(36, 174, 199, 31);
		contentPane.add(withdrawal);
		withdrawal.setColumns(10);
		
		welcomebar.setHorizontalAlignment(SwingConstants.CENTER);
		welcomebar.setBorder(null);
		welcomebar.setFont(new Font("Square721 BT", Font.BOLD | Font.ITALIC, 18));
		welcomebar.setForeground(new Color(0, 0, 0));
		welcomebar.setBackground(new Color(204, 153, 255));
		welcomebar.setBounds(10, 11, 288, 31);
		contentPane.add(welcomebar);
		balance.setHorizontalAlignment(SwingConstants.CENTER);
		
		balance.setBorder(new LineBorder(Color.BLACK, 2));
		balance.setFont(new Font("Tahoma", Font.PLAIN, 17));
		balance.setForeground(new Color(0, 0, 0));
		balance.setBackground(Color.WHITE);
		balance.setBounds(75, 337, 134, 31);
		contentPane.add(balance);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Transaction.this.dispose();
				new LoginScreen().setVisible(true);
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(97, 396, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnHistory = new JButton("Transaction History");
		btnHistory.setBackground(Color.WHITE);
		btnHistory.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String transaction_history=History.show(account_ID);
					System.out.println(transaction_history);
					if(transaction_history==null)
						textArea.setText("No Transactions Found");
					else
						textArea.setText(transaction_history);
				} catch (Exception e1) 
				{
					JOptionPane.showMessageDialog(btnHistory,"Error","Inane warning",JOptionPane.WARNING_MESSAGE, null);
				}
			}
		});
		btnHistory.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHistory.setBounds(308, 19, 213, 23);
		contentPane.add(btnHistory);
		
		textArea = new TextArea();
		textArea.setForeground(Color.WHITE);
		textArea.setBackground(Color.BLACK);
		textArea.setEditable(false);
		textArea.setBounds(252, 48, 309, 394);
		contentPane.add(textArea);
	}
}
