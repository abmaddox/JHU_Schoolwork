/**
 * @author Andrew Maddox
 */
package edu.jhuep.maddox.display;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.MaskFormatter;

import client.ClientBHC;
import edu.jhu.en605681.HikeType;
import edu.jhu.en605681.Rates;


/**
 * BhcUtil jar is required for the function of this project.
 * This class contains the components used to select a hike, hike duration, hike start date,
 *  number of hikers, and get a rate for the selected hike matching the user's criteria, if it exists.
 * Errors are shown using pop-up windows with some messages stemming
 * from the BhCUtils API and others coming from internal validation. See method getErrors for validation.
 */
public class Display{

	private JFrame frame;
	private GridBagLayout gbl;
	private GridBagConstraints gbc;
	private JPanel base;
	private JScrollPane baseScroller;

	private JLabel hikesLabel;
	private JList<String> hikes;
	private JScrollPane hikeScroller;
	private JPanel topLeftPanel;

	private JLabel durationsLabel;
	private JList<Integer> durations;
	private JScrollPane durationScroller;
	private JPanel topRightPanel;

	private JLabel tourStartEntryLabel;
	private MaskFormatter startDateFormat;
	private JFormattedTextField tourStartDateEntry;
	private JPanel midLeftPanel;

	private JLabel hikerNumberEntryLabel;
	private MaskFormatter hikerNumFormat;
	private JFormattedTextField hikerNumberEntry;
	private JPanel midRightPanel;

	private JButton submitButton;
	private JLabel calculatedRateLabel;
	private JPanel bottomPanel;

	private HikeType hikeSelected;
	private int durationSelected;
	private String tourStartDate;

	private double rate;


	public Display(String title){
		frame = new JFrame(title);
		frame.setAutoRequestFocus(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(1200, 800);
		frame.setLocationByPlatform(true);

		initializeComponents();
		setupLayout();
		frame.add(baseScroller);
		frame.pack();
	}

	public void launch() {
		frame.setVisible(true);
	}

	/*
	 * This method initializes all components with their basic traits before
	 * they are placed into the layout.
	 */
	private void initializeComponents() {
		//initializing layout objects

		//GridBagLayout used for base panel
		gbl = new GridBagLayout();
		base = new JPanel(gbl);

		//add scrolling to base pane so you can see content even in small windows
		baseScroller = new JScrollPane();
		baseScroller.setViewportView(base);
		gbc = new GridBagConstraints();

		//panels which will be placed in the GridBagLayout for more layout control
		topLeftPanel = new JPanel();
		topLeftPanel.setLayout(new BoxLayout(topLeftPanel, BoxLayout.Y_AXIS));

		topRightPanel = new JPanel();
		topRightPanel.setLayout(new BoxLayout(topRightPanel, BoxLayout.Y_AXIS));

		midLeftPanel = new JPanel();
		midLeftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));

		midRightPanel = new JPanel();
		midRightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));


		//used to control how the JLists orient text in their rows
		//each JList renderer needs to be retrieved to set the horizontal alignment
		DefaultListCellRenderer renderer;


		//initializing list of hikes
		String[] arrHikes = HikeType.getHikeNames();
		hikes = new JList<>(arrHikes);
		hikes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hikes.setLayoutOrientation(JList.VERTICAL);
		hikes.setSelectedIndex(0);

		renderer = (DefaultListCellRenderer) hikes.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		hikes.addListSelectionListener(new ListSelectionListener() {

			/*
			 * This method is called when a hike is selected or de-selected in the hike list.
			 * If a hike is selected then the list of durations changes to reflect valid durations for the selected hike.
			 * When no hike is selected the durations list is not populated and tool-tip information informs the user to select a hike first.
			 */
			@Override
			public void valueChanged(ListSelectionEvent e) {

				calculatedRateLabel.setText("Rate: ");
				if (!e.getValueIsAdjusting()) {
					//D
					//set duration list to display valid durations for the selected hike when a hike is selected
					if (hikes.getSelectedIndex() != -1) {

						Rates rates;
						for (HikeType hike : HikeType.values()) {
							if (hike.equalsName(hikes.getSelectedValue())) {
								hikeSelected = hike;
								rates = new Rates(hikeSelected);
								Integer[] durs = Arrays.stream( rates.getDurations() ).boxed().toArray( Integer[]::new );
								durations.setListData(durs);
							}
						}

					}
					//no hike is selected and the list of durations should become empty again
					else {
						//clear selection of durations list first to prevent weird side effects
						if(durations.getSelectedIndex() == -1) {
							hikeSelected = null;
							durations.clearSelection();
						}

						durations.setListData(new Integer[0]);
					}

				}
			}

		});


		//initializing list of hike durations
		durations = new JList<>(new Integer[0]);
		durations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		durations.setLayoutOrientation(JList.VERTICAL);

		renderer = (DefaultListCellRenderer) durations.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		durations.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				calculatedRateLabel.setText("Rate: ");
				if (!e.getValueIsAdjusting()) {

					//set selectedDuration to the value selected in the list
					if (durations.getSelectedIndex() != -1) {
						durationSelected = durations.getSelectedValue();
					}
					//set selectedDuration to indicate no selection was made
					else {
						durationSelected = 0;
					}
				}
			}

		});

		//creating list of hikes with scrolling functionality
		hikeScroller = new JScrollPane();
		hikeScroller.setViewportView(hikes);

		//creating list of hike durations with scrolling functionality
		durationScroller = new JScrollPane();
		durationScroller.setViewportView(durations);


		//initializing button for query submission
		submitButton = new JButton("Calculate Rate");
		submitButton.addActionListener(new ActionListener() {

			/*
			 * This method is called when the submitButton is pressed.
			 * It calls
			 * If there are error messages then it called the popUpError method to display the messages.
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatedRateLabel.setText("Rate: ");
				String message = "";
				message = getSelectionErrors();

				//selection errors take priority before message is sent to server
				//server only contacted if no selection errors exist
				if (message == ""){
					//no selection errors exist
					message = getServerMessage();
					//returned in format rate:message
					String[]splitMessage = message.split(":");
					rate = Double.parseDouble(splitMessage[0]);


					if(rate>0) {
						calculatedRateLabel.setText(calculatedRateLabel.getText() + " $" + rate);
					}
					else {
						//error message format is error1, error2, ...
						message = splitMessage[1];
						//breaks up the error message into a more readable multi-line message
						message = message.replace(",", "\n");
						popUpError(message);
					}
				}
				else {
					popUpError(message);
				}
			}
		});
		//end action listener for submit button




		//initializing labels
		hikesLabel = new JLabel("Step 1: Select a hike below");
		durationsLabel = new JLabel("Step 2: Select a duration (in days) below ");
		tourStartEntryLabel = new JLabel("Step 3: Enter the start date in MM/DD/YYYY format");
		hikerNumberEntryLabel  = new JLabel("Step 4: Enter the number of hikers attending");
		calculatedRateLabel = new JLabel("Rate: ");
		calculatedRateLabel.setHorizontalTextPosition(SwingConstants.CENTER);


		//initializing formatted text fields
		try {
			startDateFormat = new MaskFormatter("##/##/####");
			hikerNumFormat = new MaskFormatter("***");
			hikerNumFormat.setValidCharacters("1234567890 ");
			hikerNumFormat.setPlaceholderCharacter(' ');
		} catch (ParseException e1) {
			// We never reach this point so the exception is thrown away
		}

		tourStartDateEntry = new JFormattedTextField(startDateFormat);
		tourStartDateEntry.setHorizontalAlignment(SwingConstants.CENTER);
		tourStartDateEntry.setColumns(10);
		tourStartDateEntry.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				tourStartDate = tourStartDateEntry.getText().strip();
			}
		});

		hikerNumberEntry = new JFormattedTextField(hikerNumFormat);
		hikerNumberEntry.setHorizontalAlignment(SwingConstants.CENTER);
		hikerNumberEntry.setColumns(10);

		//establish default settings for all components. must be placed before the action listener for this field
		//so the code compiles
		setDefaultComponentValues();

		//adding components to their panels
		topLeftPanel.add(hikesLabel);
		topLeftPanel.add(hikeScroller);

		topRightPanel.add(durationsLabel);
		topRightPanel.add(durationScroller);

		midLeftPanel.add(tourStartEntryLabel);
		midLeftPanel.add(tourStartDateEntry);

		midRightPanel.add(hikerNumberEntryLabel);
		midRightPanel.add(hikerNumberEntry);

		bottomPanel.add(calculatedRateLabel);
		bottomPanel.add(submitButton);
	}

	//calls addComponentToLayout for each component
	private void setupLayout() {
		addComponentToLayout(topLeftPanel, 0, 0, 2, 2);
		addComponentToLayout(topRightPanel, 3, 0, 2, 2);
		addComponentToLayout(midLeftPanel, 0, 2, 2, 1);
		addComponentToLayout(midRightPanel, 3, 2, 2, 1);
		addComponentToLayout(bottomPanel, 2, 3, 1, 1);
	}

	// Method used to add a component to the JPanel GridBaglayout with default Inset value
	// resetGridBagConstraints is called at the end to prevent side effects
	private void addComponentToLayout(Component component, int gridX, int gridY, int gridWidth,  int gridHeight) {

		//general settings
		gbc.insets = new Insets(5, 10, 5, 10);
		gbc.weightx = .5;
		gbc.weighty = .5;
		gbc.fill = GridBagConstraints.BOTH;
		//custom settings per component
		gbc.gridx = gridX;
		gbc.gridy = gridY;
		gbc.gridwidth = gridWidth;
		gbc.gridheight = gridHeight;

		base.add(component, gbc);
		resetGridBagConstraints();
	}

	//Method used to prevent reuse of constraints unintentionally and reset parameters to defaults
	private void resetGridBagConstraints () {
		gbc = new GridBagConstraints();
	}

	//This is used to generate a pop-up window in the event that the user-entered information is invalid.
	//The error message presented is generated by the method getErrors
	private void popUpError(String message) {
		JOptionPane.showMessageDialog(base, message, "ERROR:", JOptionPane.ERROR_MESSAGE);
	}


	//after components are instantiated the default values for all fields are set
	public void setDefaultComponentValues() {
		//no selected hike is default for hike list
		hikes.clearSelection();

		//no selected duration and no durations shown is default for durations list
		durations.clearSelection();

		//sets the initial value to today's date so no error for lack of input can be given
		SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		String strDate = f.format(new Date());
		tourStartDateEntry.setValue(strDate);

		//sets the initial value to 1 so no error for lack of input can be given.
		hikerNumberEntry.setValue("1");

	}


	/*
	 * This method creates a ClientBHC object to communicate with the BHC server and get the return message from the server if one exists.
	 * If there is an error reaching the server then this error message overrides error messages about the values passed to the server.
	 */
	private String getServerMessage() {
		//selected hike date [0]month,[1]day, [2]year
		String[] dateValsAsString = tourStartDate.split("/");
		String numHikers = hikerNumberEntry.getText().replaceAll(" ", "");
		String message;

		//server can be contacted because all fields have an input

		//output format - hike_id:begin_month:begin_day:begin_year:duration:numberHikers
		//broken into multiple lines for simplicity
		String socketOutputMessage = hikes.getSelectedIndex() +  ":";
		socketOutputMessage = socketOutputMessage + Integer.parseInt(dateValsAsString[0]) + ":";
		socketOutputMessage = socketOutputMessage + Integer.parseInt(dateValsAsString[1]) + ":";
		socketOutputMessage = socketOutputMessage + Integer.parseInt(dateValsAsString[2]) + ":";
		socketOutputMessage = socketOutputMessage + durationSelected + ":";
		socketOutputMessage = socketOutputMessage + numHikers;

		//Logging
		System.out.println("Message sent to server: " + socketOutputMessage);

		ClientBHC client = new ClientBHC(socketOutputMessage);

		//server error messages have priority over input errors. -0.01: is what the error is instantiated to, so that alone indicates no errors
		if(client.getServerErrors() == "-0.01:") {
			message =  client.getServerOutput();
		}
		else {
			message = client.getServerErrors();
		}
		return message;
	}

	/*
	 * This method checks for information that has not been selected by the user. If either a hike, duration, or both
	 * are not selected an appropriate error message will be returned.
	 */
	private String getSelectionErrors() {
		String message = "";

		//Check for selection of a hike
		if (hikes.getSelectedIndex()==-1) {
			message = message + "No hike was selected\n";
		}

		//Check for valid duration selection
		if (durations.getSelectedIndex()==-1){
			message = message + "No duration was selected\n";
		}

		return message;
	}

}