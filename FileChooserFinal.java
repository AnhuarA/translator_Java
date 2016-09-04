//Required Libraries
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.awt.TextArea;
import javax.swing.JFrame;
import java.awt.Color;

public class FileChooserFinal extends JFrame {
	  /**
	 * 
	 */
	// Global variables required for the program
	private static final long serialVersionUID = 1L;
	private JTextField filename = new JTextField(), dir = new JTextField();
	  private JLabel label=new JLabel(),label3=new JLabel();
	  private TextArea originalTextArea = new TextArea();
	  private TextArea translatedTextArea = new TextArea();
	  private JButton open = new JButton("Open"), save = new JButton("Save"), translate=new JButton("Translate");
	  //private JButton clear = new JButton("Clear")	// The clear button could not be implemented
	  private JFileChooser c = new JFileChooser();
	  private JPanel p_1;
	  // Custom colors and font
	  private Color backGround = new Color(225, 230, 230);
	  private Font boldFont = new Font("Sans-Serif", Font.BOLD, 16);
	
	  // Variables for word count and corresponding labels
	  StringBuilder sb1 = new StringBuilder();
	  Label FileWordCountlabel = new Label("");
	  Label TranslatedWordCountlabel = new Label("");
	  StringBuilder EnglishtxtSB = new StringBuilder();
	  
	  // Variables for language select
      String languageSel = "";
      Language selectedLanguage = Language.SPANISH; 
      JComboBox<String> comboBox = new JComboBox<String>();
      
      String inputLanguageSel = "";
	  Language selectedInput = Language.ENGLISH; 
	  JComboBox<String> comboBoxInput = new JComboBox<String>();

	  // Global  variables, including global GUI components
	  boolean FromFile;
	  private final JLabel lblOriginalText = new JLabel("Original text:");
	  private final JLabel lblTranslatedText = new JLabel("Translated text:");
	  private final JLabel lblSelectLanguageTo_1 = new JLabel("Select Language to translate from:");
	  
	  // Class constructor
	  public FileChooserFinal() {
		/*
		 * This constructor consists of the creation of the GUI and the modification of
		 * GUI components.
		 */
		  
		  
		super("TextFile Tranlator");
		getContentPane().setBackground(backGround);
		setBackground(Color.WHITE);
		setTitle("TextFile Translator");
		
	    JPanel p = new JPanel(new FlowLayout());
	    p.setBackground(backGround);
	    p.setBounds(457, 639, 434, 33);
	   
	    open.addActionListener(new OpenL());
	    p.add(open);

	    translate.addActionListener(new TranslateL());
	    p.add(translate);
	    save.addActionListener(new SaveL());
	    p.add(save);
	    //clear.addActionListener(new ClearL());	//Line for clear button implementation
	    Container cp = getContentPane();
	    getContentPane().setLayout(null);
	    cp.add(p);
	    dir.setEditable(false);
	    filename.setEditable(false);
	    label.setBackground(Color.LIGHT_GRAY);
	    label.setSize(500,20);
	    p_1 = new JPanel();
	    p_1.setBounds(0, 0, 657, 40);
	    p_1.setLayout(new GridLayout(2, 1));
	    p_1.setBackground(backGround);
	    p_1.add(filename);
	    p_1.add(dir);
	    p_1.add(label);
	    p_1.add(label3);
	    cp.add(p_1);
	    originalTextArea.setEditable(true);
	    originalTextArea.setBounds(0, 86, 657, 510);
	    getContentPane().add(originalTextArea);
	    
	    
	    translatedTextArea.setBounds(663, 86, 669, 510);
	    translatedTextArea.setEditable(true);
	    getContentPane().add(translatedTextArea);
	    
	    FileWordCountlabel.setBounds(10, 602, 263, 22);
	    getContentPane().add(FileWordCountlabel);
	  
	    TranslatedWordCountlabel.setBounds(685, 602, 263, 22);
	    getContentPane().add(TranslatedWordCountlabel);
	    
	    comboBox.addItem("Arabic");
        comboBox.addItem("Chinese");
        comboBox.addItem("French");
        comboBox.addItem("German");
        comboBox.addItem("Japanese");
        comboBox.addItem("Korean");
        comboBox.addItem("Portugese");
        comboBox.addItem("Russian");
        comboBox.addItem("Spanish");
        comboBox.addItem("Swedish");
         
        comboBox.setSelectedIndex(8);
        comboBox.setBounds(260, 652, 153, 20);
        getContentPane().add(comboBox);
        
        comboBoxInput.addItem("Arabic");
	    comboBoxInput.addItem("Chinese");
	    comboBoxInput.addItem("Engilsh");
	    comboBoxInput.addItem("French");
	    comboBoxInput.addItem("German");
	    comboBoxInput.addItem("Japanese");
	    comboBoxInput.addItem("Korean");
	    comboBoxInput.addItem("Portugese");
	    comboBoxInput.addItem("Russian");
	    comboBoxInput.addItem("Spanish");
	    comboBoxInput.addItem("Swedish");
	    
	    comboBoxInput.setSelectedIndex(2);
	    comboBoxInput.setBounds(29, 652, 153, 20);
	    getContentPane().add(comboBoxInput);
         
        JLabel lblSelectLanguageTo = new JLabel("Select language to translate to:");
        lblSelectLanguageTo.setBounds(260, 627, 198, 14);
        getContentPane().add(lblSelectLanguageTo);
        lblOriginalText.setFont(boldFont);
        lblTranslatedText.setFont(boldFont);
        lblOriginalText.setBounds(10, 51, 140, 29);
        
        getContentPane().add(lblOriginalText);
        lblTranslatedText.setBounds(685, 51, 140, 29);
        
        getContentPane().add(lblTranslatedText);
        lblSelectLanguageTo_1.setBounds(32, 627, 198, 14);
        
        getContentPane().add(lblSelectLanguageTo_1);

	  }
	  
	// Class for open button action
	  class OpenL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	
	    	
	      // Demonstrate "Open" dialog:
	      int rVal = c.showOpenDialog(FileChooserFinal.this);  		// Open file chooser window
	      if (rVal == JFileChooser.APPROVE_OPTION) {				
	    	
	    	// If chosen file is valid, label correct values on GUI
	        filename.setText(c.getSelectedFile().getName());		
	        dir.setText(c.getCurrentDirectory().toString());
	        label.setText(c.getSelectedFile().getAbsolutePath());
	        
	        // Read from file and add to stringBuilder
			try{
				
				FileReader frEnglish = new FileReader(c.getSelectedFile().getAbsolutePath());
				BufferedReader brEnglish = new BufferedReader(frEnglish);
				
			
	            while(true) {
	                    String inputText = brEnglish.readLine();
	              
	                            if(inputText == null){ 
	                            	break;
	                            	}
	                            EnglishtxtSB.append(inputText);
	                            EnglishtxtSB.append("\n");
	                        }
	            
	            frEnglish.close();
	            brEnglish.close();
	            
	            // Get word count of original text
	            StringTokenizer stEng = new StringTokenizer(EnglishtxtSB.toString());
	            FileWordCountlabel.setText("Original word count: " + stEng.countTokens());
	            
	            // Add stringBuilder value to textArea
				originalTextArea.append(EnglishtxtSB.toString());
				FromFile=true; 									// Make value true for "translate" button evaluation
				}
				catch (FileNotFoundException e1)
				{
					JOptionPane.showMessageDialog(null, "File Not Found", "Error",JOptionPane.ERROR_MESSAGE);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
			}
		
	      //Dialog if user cancels file selection
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	        filename.setText("You pressed cancel");
	        dir.setText("");
	      }
	    }
	  }
	  
	// Class for save button action
	  class SaveL implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
	    	// Demonstrate "Save" dialog:
		    int rVal = c.showSaveDialog(FileChooserFinal.this);
		    filename.setText(c.getSelectedFile().getName());
		    dir.setText(c.getCurrentDirectory().toString());

		    // If new text file name is approved, transfer value of the translated textArea write it to the new file
		    if (rVal == JFileChooser.APPROVE_OPTION) {
		    	filename.setText(c.getSelectedFile().getName());
		        dir.setText(c.getCurrentDirectory().toString());
		        try {
		        	FileWriter translatewriter= new FileWriter(c.getSelectedFile().getAbsolutePath());

		        	String[] line=translatedTextArea.getText().split("\n");
		        	ArrayList<String>arrList = new ArrayList<>(Arrays.asList(line)) ;
		        	StringBuilder sb1 = new StringBuilder();
		        	for (String s1 : arrList){
			           sb1.append(s1.toString());
			           sb1.append("\n"); 
			           }   
		        	translatewriter.write(sb1.toString());
		        	translatewriter.close();
		        } catch (IOException e1) {
		        	// TODO Auto-generated catch block
		        	e1.printStackTrace();
		        } catch (Exception e1) {
		        	// TODO Auto-generated catch block
		        	e1.printStackTrace();
		        	}
		        }
		                  
		      // Dialog for user canceling in save window 
		      if (rVal == JFileChooser.CANCEL_OPTION) {
		        filename.setText("You pressed cancel");
		        dir.setText("");
		        }
		      }
		    }
	 
// Class for translate button action
	  class TranslateL implements ActionListener {
		  // Demonstrate "Translate" dialog:
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		// checks if to read from a selected file, or from textArea
		    		if (FromFile==true)
		    			readfile();
		    		else
		    		translateonly(originalTextArea.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					}
		    	}
		    }
/*
 * Class for clear button action
	  class ClearL implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		    	String text="";
		    	textArea_1.setText(text);
		  }
	  }
	  
*/
	  
	  // Method to translate the content of a text file
	  public void readfile() throws Exception
	  { 
		// Client values required to use translate API
		Translate.setClientId("garychen82");
	    Translate.setClientSecret("mE6UKebm/K08j4EVyJ66brebg0DF5qW3tLvCQdvwwj0=");
	    
	    // String and stringBuilder for translation
		String translatedText1 = null ;
		StringBuilder sb = new StringBuilder();
		
		//Update input and output languages
		updateInputLanguage();
		updateLanguage();
		 
		// Read from selected file and add translation to sb 
		try{
			FileReader fr1 = new FileReader(c.getSelectedFile().getAbsolutePath());
			BufferedReader br1 = new BufferedReader(fr1);
			
			while(true) {
				String inputText = br1.readLine();
				if(inputText == null){
					break;
					}
				translatedText1 = Translate.execute(inputText, selectedInput, selectedLanguage);
				sb.append(translatedText1);
				sb.append("\n");
				}
			fr1.close();
			br1.close();
			
			// Add values of stringBuilder to translation textArea
			translatedTextArea.append(sb.toString());
			// Get word count of translated content
			StringTokenizer STtranlsated = new StringTokenizer(sb.toString());
			TranslatedWordCountlabel.setText("Translated file word count: "+ STtranlsated.countTokens());
			FromFile=false;
			}
		catch (FileNotFoundException e1)
		{
			JOptionPane.showMessageDialog(null, "File Not Found", "Error",JOptionPane.ERROR_MESSAGE);
			}
	    	
	  }
	  
	  // Method to translate the content of the text area of no file was chosen
	  public void translateonly(String s) throws Exception{
		Translate.setClientId("garychen82");
		Translate.setClientSecret("mE6UKebm/K08j4EVyJ66brebg0DF5qW3tLvCQdvwwj0=");
	    
		// Strings and stringBuilder for translation
	    String translatedText1 = null ;
	    StringBuilder sb = new StringBuilder();
	    String a = originalTextArea.getText();
	    
	    //Update input and output languages
	    updateInputLanguage();
	    updateLanguage();
	    
	    // Create string array from content of original textArea
	    String[] line=originalTextArea.getText().split("\n");
	    ArrayList<String>arrList = new ArrayList<>(Arrays.asList(line)) ;
	    StringBuilder sb1 = new StringBuilder();
	    
	    // Get and display original word count
	    StringTokenizer STtxtoriginal = new StringTokenizer(a);
	    FileWordCountlabel.setText("Original file word count: "+ STtxtoriginal.countTokens());
	    
	    // Translate content of array
	    for (String s1 : arrList){
	    	translatedText1 = Translate.execute(s1.toString(), selectedInput, selectedLanguage);
	    	sb1.append(translatedText1);
	    	sb1.append("\n");
	    	}
	    sb.append(translatedText1);
	    sb.append("\n");
	    
	    // Add translated content to translation textArea
	    translatedTextArea.append(sb1.toString());
	    
	    // Get and display translated word count
	    StringTokenizer STtexttranlsated = new StringTokenizer(sb1.toString());
	    TranslatedWordCountlabel.setText("Translated file word count: "+ STtexttranlsated.countTokens());
	    }
	 	  
	  // Method to check Input language comboBox and assign its value to the input language variable
	  public void updateInputLanguage(){
		  
			inputLanguageSel = (String) comboBoxInput.getSelectedItem();
				  
			if (inputLanguageSel.equals("Spanish")) {
				  selectedInput = Language.SPANISH;
				  } 
			else if (inputLanguageSel.equals("English")) {
				  selectedInput = Language.ENGLISH;
		        }
			else if (inputLanguageSel.equals("German")) {
				  selectedInput = Language.GERMAN;
		        }
			else if (inputLanguageSel.equals("French")) {
				  selectedInput = Language.FRENCH;
		        }
			else if (inputLanguageSel.equals("Japanese")) {
				  selectedInput = Language.JAPANESE;
		        }
			else if (inputLanguageSel.equals("Chinese")) {
				  selectedInput = Language.CHINESE_TRADITIONAL;
		        }
			else if (inputLanguageSel.equals("Korean")) {
				  selectedInput = Language.KOREAN;
		        }
			else if (inputLanguageSel.equals("Portugese")) {
				  selectedInput = Language.PORTUGUESE;
		        }
			else if (inputLanguageSel.equals("Russian")) {
				  selectedInput = Language.RUSSIAN;
		        }
			else if (inputLanguageSel.equals("Swedish")) {
				  selectedInput = Language.SWEDISH;
		        }
			else if (inputLanguageSel.equals("Arabic")) {
				  selectedInput = Language.ARABIC;
		        }
		}
	  
	// Method to check translated language comboBox and assign its value to the translated language variable
	  public void updateLanguage(){
          
          languageSel = (String) comboBox.getSelectedItem();
           
          if (languageSel.equals("Spanish")) {
               selectedLanguage = Language.SPANISH;
            } 
          else if (languageSel.equals("German")) {
              selectedLanguage = Language.GERMAN;
            }
          else if (languageSel.equals("French")) {
              selectedLanguage = Language.FRENCH;
            }
          else if (languageSel.equals("Japanese")) {
              selectedLanguage = Language.JAPANESE;
            }
          else if (languageSel.equals("Chinese")) {
              selectedLanguage = Language.CHINESE_TRADITIONAL;
            }
          else if (languageSel.equals("Korean")) {
              selectedLanguage = Language.KOREAN;
            }
          else if (languageSel.equals("Portugese")) {
              selectedLanguage = Language.PORTUGUESE;
            }
          else if (languageSel.equals("Russian")) {
              selectedLanguage = Language.RUSSIAN;
            }
          else if (languageSel.equals("Swedish")) {
              selectedLanguage = Language.SWEDISH;
            }
          else if (languageSel.equals("Arabic")) {
              selectedLanguage = Language.ARABIC;
            }
      }
	  
	  // Main method to run class
	  public static void main(String[] args) {
	    run(new FileChooserFinal(), 1375, 720);
	  }
	  
	  // Method used to call the class
	  public static void run(JFrame frame, int width, int height) {
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(width, height);
	    frame.setVisible(true);
	  }
	} ///:~
