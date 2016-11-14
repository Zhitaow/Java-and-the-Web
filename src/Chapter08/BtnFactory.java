package Chapter08;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
/**
 * The button factory
 * @author Zhitao Wang
 * @version 1.0
 * @since 11/12/2016
 */
public class BtnFactory {
	private List<JButton> btnList = new ArrayList<JButton>();
	private CalLogic cal;
	public void register(CalculatorLayout calLayout, StackArray stack, String[] status) {
		btnList = calLayout.getBtn();
		for (JButton btn:btnList) {
			btnActionPerformed(btn, calLayout, stack, status);
		}
	}

	public void btnActionPerformed(final JButton btn, final CalculatorLayout calLayout, final StackArray stack, final String[] status) {
		btn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			cal = new CalLogic(btn, calLayout, stack, status);
    			cal.update();
    		}
		});
	}
	
}
