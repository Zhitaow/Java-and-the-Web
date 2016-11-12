//package Chapter08;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
/**
 * The calculator layout interface
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
public interface CalculatorLayout {
	// collect all buttons in the content pane
	public void collectBtn(JPanel contentPane);
	
	public JButton getBtn(String label);
	
	public List<JButton> getBtn();
	
	public void setDisplayText(String message);
	
	public String getDisplayText();
	
	public void setLogText(String message);
	
	public String getLogText();
	
	public void setLayout(JPanel contentPane);
	
	public String getSelectedItem();
	
	public JComboBox<String> getJComboBox();
	
}
