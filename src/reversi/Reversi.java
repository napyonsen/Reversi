/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reversi;

/**
 *
 * @author geral
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.*;

public class Reversi extends JFrame implements ActionListener 
{
     gamePlay othelloGamePlay = new gamePlay();
     glbIntCls init_var = new glbIntCls();
      boolean temp_b ;
    long start_time =0;
    long execute_time =0;
    
           
     boolean startSteppingB00 = false;
     int turn_count =0;
     int game_history_count =0;
     boolean table_indicator=false;
     int black_num_discs=0;
     int white_num_discs=0;
     
     
    JPanel game_area;
    JLabel curr_player_disp;
    JLabel curr_player_sq;
    
    JLabel black_agent_game_area;
    JLabel white_agent_game_area;
    JLabel black_disc_num;
    JLabel white_disc_num;
    JLabel black_disc;
    JLabel white_disc;
    JLabel black_time_num;
    JLabel white_time_num;
    JLabel black_time;
    JLabel white_time;
    JButton board_area[][];
    JButton board_column[];
    JButton board_row[];
 
    JPanel start_panel;
    JButton start_button;
    JButton reset_button;
    JButton step_button;
    
    JPanel options_area;
    JLabel options_label;
    JLabel black_options;
    
    JLabel black_stage_weight;
    
    JComboBox black_stage_combo;
    JButton black_done;
    JLabel black_stage_names;
    JLabel black_maxDisc;
    JLabel black_mobility;
    JLabel black_stability;
    JLabel black_frontier;
    
    JTextField black_maxDisc_value;
    JLabel between1_10_b1;
    JTextField black_mobility_value;
    JLabel between1_10_b2;
    JTextField black_stability_value;
    JLabel between1_10_b3;
    JTextField black_frontier_value;
    JLabel between1_10_b4;
  
    JComboBox white_stage_combo;
    JButton white_done;
    JLabel white_stage_names;
    JLabel white_maxDisc;
    JLabel white_mobility;
    JLabel white_stability;
    JLabel white_frontier;
    
    JTextField white_maxDisc_value;
    JLabel between1_10_w1;
    JTextField white_mobility_value;
    JLabel between1_10_w2;
    JTextField white_stability_value;
    JLabel between1_10_w3;
    JTextField white_frontier_value;
    JLabel between1_10_w4;
    
    JLabel white_options;
    JLabel black_openings_label;
    JLabel white_openings_label;
    JComboBox black_openings;
    JComboBox white_openings;
    JLabel black_heuristic_label;
    JLabel white_heuristic_label;
    JComboBox black_heuristic;
    JComboBox white_heuristic;
    JButton seperator;
    JCheckBox cpu_vs_cpu;
    JLabel cpu_vs_cpu_label;
    JCheckBox player_vs_cpu;
    JLabel player_vs_cpu_label;
    JButton seperator_2;
    JTable position_table;
    JScrollPane scrollpane;
	ImageIcon candidate_image;
	ImageIcon black_image ;
	ImageIcon white_image;

    public Reversi ()
    {
        
  
		this.setTitle("Reversi Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setBounds(0,0,1250,650);
		this.setLayout(null);
		
    
    
		//OPTIONS PANEL
		options_area = new JPanel();
		options_area.setLayout(null);
		options_area.setBounds(800, 0, 450, 650);
		options_area.setBackground(Color.white);
		this.add(options_area);
		
		options_label = new JLabel("OPTIONS");
		options_label.setBounds(10, 10, 150, 20);
		options_label.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 12));
		options_label.setBorder(BorderFactory.createLineBorder(Color.black));
		options_label.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(options_label);
		
		black_options = new JLabel("BLACK STAGE WEIGHTS");
		black_options.setBounds(30, 55, 140, 20);
		black_options.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_options.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(black_options);
		
		
		black_stage_names = new JLabel("STAGES");
		black_stage_names.setBounds(15, 105, 120, 15);
		black_stage_names.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_stage_names.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(black_stage_names);
		
		String[] stage_name_string = {"Early Game", "Mid Game", "End Game"}; 
		
		black_stage_combo = new JComboBox(stage_name_string);
		black_stage_combo.setBounds(70, 100, 90, 20);
		black_stage_combo.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_stage_combo.addActionListener(this);
		options_area.add(black_stage_combo);
		
		
		black_maxDisc = new JLabel("Max Disc");
		black_maxDisc.setBounds(15, 170, 100, 15);
		black_maxDisc.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_maxDisc.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(black_maxDisc);
		
		black_maxDisc_value = new JTextField("1");
		black_maxDisc_value.setBounds(70, 167, 30, 20);
		black_maxDisc_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_maxDisc_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(black_maxDisc_value);
		
		between1_10_b1 = new JLabel("( 1-10 )");
		between1_10_b1.setBounds(110, 167, 40, 20);
		between1_10_b1.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_b1.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_b1);
    
		black_mobility_value = new JTextField("1");
		black_mobility_value.setBounds(70, 197, 30, 20);
		black_mobility_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_mobility_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(black_mobility_value);
		
		between1_10_b2 = new JLabel("( 1-10 )");
		between1_10_b2.setBounds(110, 197, 40, 20);
		between1_10_b2.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_b2.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_b2);
    
		black_stability_value = new JTextField("1");
		black_stability_value.setBounds(70, 227, 30, 20);
		black_stability_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_stability_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(black_stability_value);
		
		between1_10_b3 = new JLabel("( 1-10 )");
		between1_10_b3.setBounds(110, 227, 40, 20);
		between1_10_b3.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_b3.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_b3);
    
		black_frontier_value = new JTextField("1");
		black_frontier_value.setBounds(70, 257, 30, 20);
		black_frontier_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_frontier_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(black_frontier_value);
		
		between1_10_b4 = new JLabel("( 1-10 )");
		between1_10_b4.setBounds(110, 257, 40, 20);
		between1_10_b4.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_b4.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_b4);
    
		black_mobility = new JLabel("Mobility");
		black_mobility.setBounds(15, 200, 100, 15);
		black_mobility.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_mobility.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(black_mobility);
		
		black_stability = new JLabel("Stability");
		black_stability.setBounds(15, 230, 100, 15);
		black_stability.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_stability.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(black_stability);
		
		black_frontier = new JLabel("Frontier");
		black_frontier.setBounds(15, 260, 100, 15);
		black_frontier.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		black_frontier.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(black_frontier);
		
		black_done = new JButton("DONE");
		black_done.setBounds(17, 280, 80, 25);
		black_done.setFont(new Font("Arial",Font.TYPE1_FONT, 11));
		black_done.setHorizontalAlignment(JLabel.CENTER);
		black_done.setBorder(BorderFactory.createLineBorder(Color.black));
		black_done.setBackground(Color.white);
		black_done.setOpaque(true);
		black_done.addActionListener(this);
		options_area.add(black_done);

		white_options = new JLabel("WHITE STAGE WEIGHTS");
		white_options.setBounds(270, 55, 140, 20);
		white_options.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_options.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(white_options);
		
		white_stage_names = new JLabel("STAGES");
		white_stage_names.setBounds(255, 105, 120, 15);
		white_stage_names.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_stage_names.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(white_stage_names);
		
		white_stage_combo = new JComboBox(stage_name_string);
		white_stage_combo.setBounds(310, 100, 90, 20);
		white_stage_combo.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_stage_combo.addActionListener(this);
		options_area.add(white_stage_combo);
		
		white_maxDisc = new JLabel("Max Disc");
		white_maxDisc.setBounds(255, 170, 100, 15);
		white_maxDisc.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_maxDisc.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(white_maxDisc);
		
		white_maxDisc_value = new JTextField("1");
		white_maxDisc_value.setBounds(310, 167, 30, 20);
		white_maxDisc_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_maxDisc_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(white_maxDisc_value);
		
		between1_10_w1 = new JLabel("( 1-10 )");
		between1_10_w1.setBounds(350, 167, 40, 20);
		between1_10_w1.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_w1.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_w1);
    
		white_mobility_value = new JTextField("1");
		white_mobility_value.setBounds(310, 197, 30, 20);
		white_mobility_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_mobility_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(white_mobility_value);
		
		between1_10_w2 = new JLabel("( 1-10 )");
		between1_10_w2.setBounds(350, 197, 40, 20);
		between1_10_w2.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_w2.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_w2);
    
		white_stability_value = new JTextField("1");
		white_stability_value.setBounds(310, 227, 30, 20);
		white_stability_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_stability_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(white_stability_value);
			
		between1_10_w3 = new JLabel("( 1-10 )");
		between1_10_w3.setBounds(350, 227, 40, 20);
		between1_10_w3.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_w3.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_w3);
    
		white_frontier_value = new JTextField("1");
		white_frontier_value.setBounds(310, 257, 30, 20);
		white_frontier_value.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_frontier_value.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(white_frontier_value);
		
		between1_10_w4 = new JLabel("( 1-10 )");
		between1_10_w4.setBounds(350, 257, 40, 20);
		between1_10_w4.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		between1_10_w4.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(between1_10_w4);
    
		white_mobility = new JLabel("Mobility");
		white_mobility.setBounds(255, 200, 100, 15);
		white_mobility.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_mobility.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(white_mobility);
		
		white_stability = new JLabel("Stability");
		white_stability.setBounds(255, 230, 100, 15);
		white_stability.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_stability.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(white_stability);
		
		white_frontier = new JLabel("Frontier");
		white_frontier.setBounds(255, 260, 100, 15);
		white_frontier.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		white_frontier.setHorizontalAlignment(JLabel.LEFT);
		options_area.add(white_frontier);
		
		white_done = new JButton("DONE");
		white_done.setBounds(257, 280, 80, 25);
		white_done.setFont(new Font("Arial",Font.TYPE1_FONT, 11));
		white_done.setHorizontalAlignment(JLabel.CENTER);
		white_done.setBorder(BorderFactory.createLineBorder(Color.black));
		white_done.setBackground(Color.white);
		white_done.setOpaque(true);
		white_done.addActionListener(this);
		options_area.add(white_done);
    
		seperator = new JButton();
		seperator.setBounds(0, 310, 450, 1);
		seperator.setBorder(BorderFactory.createLineBorder(Color.black));
		seperator.setBackground(Color.black);
		seperator.setOpaque(true);
		options_area.add(seperator);  
		
		cpu_vs_cpu = new JCheckBox();
		cpu_vs_cpu.setBounds(10, 330, 30, 30);
		cpu_vs_cpu.setBackground(Color.white);
		cpu_vs_cpu.setOpaque(true);
		cpu_vs_cpu.addActionListener(this);
		options_area.add(cpu_vs_cpu);
		
		cpu_vs_cpu_label = new JLabel("Cpu vs. Cpu");
		cpu_vs_cpu_label.setBounds(35, 335, 90, 20);
		cpu_vs_cpu_label.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		cpu_vs_cpu_label.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(cpu_vs_cpu_label);   

		player_vs_cpu = new JCheckBox();
		player_vs_cpu.setBounds(10, 390, 30, 30);
		player_vs_cpu.setBackground(Color.white);
		player_vs_cpu.addActionListener(this);
		player_vs_cpu.setOpaque(true);
		options_area.add(player_vs_cpu);
		
		player_vs_cpu_label = new JLabel("Player vs. Cpu");
		player_vs_cpu_label.setBounds(40, 395, 90, 20);
		player_vs_cpu_label.setFont(new Font("Times New Roman",Font.TYPE1_FONT, 11));
		player_vs_cpu_label.setHorizontalAlignment(JLabel.CENTER);
		options_area.add(player_vs_cpu_label);  
		
		position_table = new JTable(64,3);
		scrollpane = new JScrollPane(position_table);
		position_table.getTableHeader().getColumnModel().getColumn(0).setHeaderValue("#");
		position_table.getTableHeader().getColumnModel().getColumn(1).setHeaderValue("Player");
		position_table.getTableHeader().getColumnModel().getColumn(2).setHeaderValue("Position");
		scrollpane.setBounds(200, 340, 200, 250);
		options_area.add(scrollpane);
		
    
		//GAME AREA PANEL
		game_area = new JPanel();
		game_area.setLayout(null);
		game_area.setBounds(0, 0, 800, 500);
		game_area.setBackground(Color.lightGray);
		this.add(game_area);
		
		curr_player_disp = new JLabel("CURRENT PLAYER");
		curr_player_disp.setBounds(300, 30, 150, 10);
		curr_player_disp.setFont(new Font("Arial",Font.TYPE1_FONT, 14));
		curr_player_disp.setHorizontalAlignment(JLabel.LEFT);
		game_area.add(curr_player_disp);
		
		curr_player_sq = new JLabel();
		curr_player_sq.setBounds(445, 25, 20, 20);
		curr_player_sq.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		curr_player_sq.setHorizontalAlignment(JLabel.LEFT);
		curr_player_sq.setBackground(Color.white);
		curr_player_sq.setOpaque(true);
		game_area.add(curr_player_sq);
    
    
		float[] hsb = Color.RGBtoHSB(0, 102, 0, null);
		board_area=new JButton[8][8];
		for ( int i=0; i<8; i++)
		{
             for (int  j=0; j<8 ; j++)
			{
				board_area[i][j] = new JButton();
				board_area[i][j].setBounds((j+5)*48,(i+2)*48,48,48);
				board_area[i][j].setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
				
				board_area[i][j].setOpaque(true);
				
				game_area.add(board_area[i][j]);
                                board_area[i][j].addActionListener(this);
             }
		}
     
		board_column = new JButton[8];
		String[] board_column_value = {"1","2", "3", "4", "5","6","7","8"};
		board_row = new JButton[8];
		String[] board_row_value = {"A","B", "C", "D", "E","F","G","H"};
	
		for ( int i=0; i<8; i++)
		{
			board_column[i] = new JButton();
			board_column[i].setBounds(200,(i+2)*48,40,48);
			board_column[i].setBackground(Color.darkGray);
			board_column[i].setText(board_column_value[i]);
			board_column[i].setEnabled(false);
			board_column[i].setForeground(Color.white);
			board_column[i].setFont(new Font("Times New Roman",Font.CENTER_BASELINE, 8));
			board_column[i].setOpaque(true);
			game_area.add(board_column[i]);
          
               }
      
		for ( int i=0; i<8; i++)
		{
		board_row[i] = new JButton();
		board_row[i].setBounds(48*(i+5),55,48,40);
		board_row[i].setBackground(Color.darkGray);
		board_row[i].setText(board_row_value[i]);
		board_row[i].setEnabled(false);
		board_row[i].setForeground(Color.white);
		board_row[i].setFont(new Font("Times New Roman",Font.CENTER_BASELINE, 8));
		board_row[i].setOpaque(true);
		game_area.add(board_row[i]);
		
		}
     
    
		//START PANEL
		start_panel = new JPanel();
		start_panel.setLayout(null);
		start_panel.setBounds(0, 500, 800, 150);
		start_panel.setBackground(Color.lightGray);
		this.add(start_panel);       
		
		step_button = new JButton("STEP");
		step_button.setBounds(20, 20, 100, 30);
		step_button.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		step_button.setHorizontalAlignment(JLabel.CENTER);
		step_button.setBorder(BorderFactory.createLineBorder(Color.black));
		step_button.setBackground(Color.white);
		step_button.setOpaque(true);
		step_button.addActionListener(this);
		start_panel.add(step_button);
		
		start_button = new JButton("START");
		start_button.setBounds(20, 70, 100, 30);
		start_button.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		start_button.setHorizontalAlignment(JLabel.CENTER);
		start_button.setBorder(BorderFactory.createLineBorder(Color.black));
		start_button.setBackground(Color.white);
		start_button.setOpaque(true);
		start_button.addActionListener(this);
		start_panel.add(start_button);
		
		black_agent_game_area = new JLabel("Black");
		black_agent_game_area.setBounds(280, 15, 60, 20);
		black_agent_game_area.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		black_agent_game_area.setHorizontalAlignment(JLabel.LEFT);
		start_panel.add(black_agent_game_area);
		
		black_image = new ImageIcon("ico_black_1.jpg");
		JLabel black_icon = new JLabel(black_image);
		black_icon.setBounds(290, 0, 90, 50);
		black_icon.setBorder(null);
		black_icon.setBackground(Color.lightGray);
		black_icon.setOpaque(true);
		start_panel.add(black_icon);
		
			
		white_image = new ImageIcon("ico_white_1.jpg");
		JLabel white_icon = new JLabel(white_image);
		white_icon.setBounds(430, 0, 90, 50);
		white_icon.setBorder(null);
		white_icon.setBackground(Color.lightGray);
		white_icon.setOpaque(true);
		start_panel.add(white_icon);
		
		white_agent_game_area = new JLabel("White");
		white_agent_game_area.setBounds(390, 15, 60, 20);
		white_agent_game_area.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		white_agent_game_area.setHorizontalAlignment(JLabel.LEFT);
		start_panel.add(white_agent_game_area);
		
		candidate_image = new ImageIcon("candidate.jpg");
		
		black_disc_num = new JLabel("2");
		black_disc_num.setBorder(BorderFactory.createLineBorder(Color.black));
		black_disc_num.setBounds(280, 50, 40, 20);
		black_disc_num.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		black_disc_num.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(black_disc_num);
		
		white_disc_num = new JLabel("2");
		white_disc_num.setBorder(BorderFactory.createLineBorder(Color.black));
		white_disc_num.setBounds(410, 50, 40, 20);
		white_disc_num.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		white_disc_num.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(white_disc_num);
				
    
            
		black_disc = new JLabel("Discs");
		black_disc.setBounds(340, 50, 40, 20);
		black_disc.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		black_disc.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(black_disc);    
		
		white_disc = new JLabel("Discs");
		white_disc.setBounds(470, 50, 40, 20);
		white_disc.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		white_disc.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(white_disc);
		
    
            
		black_time_num = new JLabel("0");
		black_time_num.setBorder(BorderFactory.createLineBorder(Color.black));
		black_time_num.setBounds(280, 80, 60, 20);
		black_time_num.setFont(new Font("Arial",Font.TYPE1_FONT, 11));
		black_time_num.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(black_time_num);
		
		white_time_num = new JLabel("0");
		white_time_num.setBorder(BorderFactory.createLineBorder(Color.black));
		white_time_num.setBounds(410, 80, 60, 20);
		white_time_num.setFont(new Font("Arial",Font.TYPE1_FONT, 11));
		white_time_num.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(white_time_num);
		
    
		black_time = new JLabel("Time (ms)");
		black_time.setBounds(340, 80, 60, 20);
		black_time.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		black_time.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(black_time);   
		

		white_time = new JLabel("Time (ms)");
		white_time.setBounds(470, 80, 60, 20);
		white_time.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		white_time.setHorizontalAlignment(JLabel.CENTER);
		start_panel.add(white_time); 
		
		reset_button = new JButton("RESET");
		reset_button.setBounds(640, 70, 100, 30);
		reset_button.setFont(new Font("Arial",Font.TYPE1_FONT, 12));
		reset_button.setHorizontalAlignment(JLabel.CENTER);
		reset_button.setBorder(BorderFactory.createLineBorder(Color.black));
		reset_button.setBackground(Color.white);
		reset_button.setForeground(Color.red);
		reset_button.setOpaque(true);
		reset_button.addActionListener(this);
		start_panel.add(reset_button);   
                
        pushToScreen(othelloGamePlay.gameBoard);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Reversi ReversiDemo = new Reversi();
       ReversiDemo.setVisible(true);
    }
    
    public void AI_AutoPlay(){
        
        if(othelloGamePlay.humanTurnB00 == false){
				if(!othelloGamePlay.gameBoard.isGameOver()) 
				{   
					pushToScreen(othelloGamePlay.gameBoard);
					displayNumOfDiscs();
					
					//startSteppingB00 = true;
                                        
					othelloGamePlay.forwardGameOneStep();
                                        
                                        updateTable(othelloGamePlay);
                                        pushToScreen(othelloGamePlay.gameBoard);
				}
				if(othelloGamePlay.gameBoard.isGameOver()) 
				{  
					pushToScreen(othelloGamePlay.gameBoard);
					
					step_button.setEnabled(false);
				}
				othelloGamePlay.humanTurnB00 = true;
			}
        
    }

   
public void actionPerformed(ActionEvent e){
	

    if(e.getSource()==reset_button){
        othelloGamePlay = new gamePlay();
        pushToScreen(othelloGamePlay.gameBoard);
        init_var = new glbIntCls();
        cpu_vs_cpu.setSelected(false);
        player_vs_cpu.setSelected(false);
        startSteppingB00 = false;
        start_time =0;
        execute_time =0;
        turn_count =0;
        game_history_count =0;
        table_indicator=false;
        black_num_discs=0;
        white_num_discs=0;
         init_var.B_maxDiscWeightEarly = 1;
         init_var.B_mobilityWeigthEarly = 1;
         init_var.B_stableDiscWeightEarly = 1;
         init_var.B_frontierWeightEarly =1;
         init_var.B_maxDiscWeightMid = 1;
         init_var.B_mobilityWeigthMid = 1;
         init_var.B_stableDiscWeightMid = 1;
         init_var.B_frontierWeightMid = 1;
         init_var.B_maxDiscWeightEnd = 1;
         init_var.B_mobilityWeigthEnd =1;
         init_var.B_stableDiscWeightEnd = 1;
         init_var.B_frontierWeightEnd=1;
         init_var.W_maxDiscWeightEarly = 1;
         init_var.W_mobilityWeigthEarly = 1;
         init_var.W_stableDiscWeightEarly = 1;
         init_var.W_frontierWeightEarly = 1;
         init_var.W_maxDiscWeightMid = 1;
         init_var.W_mobilityWeigthMid = 1;
         init_var.W_stableDiscWeightMid = 1;
         init_var.W_frontierWeightMid = 1;
         init_var.W_maxDiscWeightEnd = 1;
         init_var.W_mobilityWeigthEnd =1;
         init_var.W_stableDiscWeightEnd = 1;
         init_var.W_frontierWeightEnd= 1;
         
         for (int i=0; i<64; i++)
         {
              position_table.setValueAt("", i, 0);
              position_table.setValueAt("", i, 1);
              position_table.setValueAt("", i, 2);
         }
         
         black_time_num.setText("0");
         white_time_num.setText("0");
         black_disc_num.setText("2");
         white_disc_num.setText("2");
         
         start_panel.revalidate();
         start_panel.repaint();
         start_panel.paintImmediately(280,0,200,100);
         
         options_area.revalidate();
         options_area.repaint();
         options_area.paintImmediately(200, 340, 200, 250);

         start_panel.revalidate();
         start_panel.repaint();
         start_panel.paintImmediately(280, 80, 200, 100);
       
      }
		if(othelloGamePlay.humanTurnB00  && othelloGamePlay.playervscpuB00 ){
			if(true/*startSteppingB00*/){
				for(short i = 0; i < 8; i++){
					for(short j = 0; j < 8; j++){
						if(e.getSource() == board_area[i][j]){
							othelloGamePlay.setHumanCell(i,j);
							othelloGamePlay.forwardGameOneStep();
							pushToScreen(othelloGamePlay.gameBoard);
                                                        othelloGamePlay.humanTurnB00 = false;
                                                         
						     updateTable(othelloGamePlay);
					             startSteppingB00 = true;
                                                        delayFunction();
                                                        AI_AutoPlay();
                                                        
							break;
						}
					}
				}
			}
		}

		if(e.getSource() == step_button)  
		{
			//[[# code to forward game step by step, at each press
			// game depth increases by one
			if(othelloGamePlay.humanTurnB00 == false && othelloGamePlay.playervscpuB00){
				if(!othelloGamePlay.gameBoard.isGameOver()) 
				{   
					pushToScreen(othelloGamePlay.gameBoard);
					displayNumOfDiscs();
					othelloGamePlay.forwardGameOneStep();
                    pushToScreen(othelloGamePlay.gameBoard);
				}
				if(othelloGamePlay.gameBoard.isGameOver()) 
				{  
					pushToScreen(othelloGamePlay.gameBoard);
					updateTable(othelloGamePlay);
					step_button.setEnabled(false);
				}
				othelloGamePlay.humanTurnB00 = true;
			}
			else if(othelloGamePlay.cpuvscpuB00){
			
				if(!othelloGamePlay.gameBoard.isGameOver()) 
				{   
					pushToScreen(othelloGamePlay.gameBoard);
                    temp_b = othelloGamePlay.gameBoard.playerTurn;
                    start_time = System.currentTimeMillis();
					othelloGamePlay.forwardGameOneStep();
                    execute_time = System.currentTimeMillis() - start_time;
                    updateTable(othelloGamePlay);
                    pushToScreen(othelloGamePlay.gameBoard);
                    displayNumOfDiscs();
				}
				if(othelloGamePlay.gameBoard.isGameOver()) 
				{  
					pushToScreen(othelloGamePlay.gameBoard);
				}
			}
		}
			
			
		
		if(e.getSource() == start_button)
		{ 
             
			if(othelloGamePlay.cpuvscpuB00){
            othelloGamePlay.comtocomPlayB00 = true;
			
				while(!othelloGamePlay.gameBoard.isGameOver()) 
				{
					pushToScreen(othelloGamePlay.gameBoard);
					displayNumOfDiscs();
					delayFunction();
									start_time = System.currentTimeMillis();
									
					othelloGamePlay.forwardGameOneStep();
									execute_time = System.currentTimeMillis() - start_time;
					updateTable(othelloGamePlay);
				}
				pushToScreen(othelloGamePlay.gameBoard);
				displayNumOfDiscs();
			}
		}
		
		if(e.getSource() == cpu_vs_cpu)
         
		{
			cpu_vs_cpu.setSelected(true) ;
			player_vs_cpu.setSelected(false);
			othelloGamePlay.playervscpuB00 = false; 
			othelloGamePlay.cpuvscpuB00 = true;
			othelloGamePlay.comtocomPlayB00 = true;
		}
		
		if(e.getSource()==player_vs_cpu)
		{
			cpu_vs_cpu.setSelected(false) ;
			player_vs_cpu.setSelected(true); 
			othelloGamePlay.playervscpuB00 = true; 
			othelloGamePlay.cpuvscpuB00 = false;
			othelloGamePlay.comtocomPlayB00 = false;
		}
     
      if(e.getSource()==black_stage_combo)
      {
          String temp;
          if (black_stage_combo.getSelectedIndex()==0)
          {
              temp=Integer.toString(init_var.B_maxDiscWeightEarly);
              black_maxDisc_value.setText(temp);
              temp=Integer.toString(init_var.B_mobilityWeigthEarly);
              black_mobility_value.setText(temp);
              temp=Integer.toString(init_var.B_stableDiscWeightEarly);
              black_stability_value.setText(temp);
              temp=Integer.toString(init_var.B_frontierWeightEarly);
              black_frontier_value.setText(temp);   
          }
          
           if (black_stage_combo.getSelectedIndex()==1)
          {
              temp=Integer.toString(init_var.B_maxDiscWeightMid);
              black_maxDisc_value.setText(temp);
              temp=Integer.toString(init_var.B_mobilityWeigthMid);
              black_mobility_value.setText(temp);
              temp=Integer.toString(init_var.B_stableDiscWeightMid);
              black_stability_value.setText(temp);
              temp=Integer.toString(init_var.B_frontierWeightMid);
              black_frontier_value.setText(temp);   
          }
           
            if (black_stage_combo.getSelectedIndex()==2)
          {
              temp=Integer.toString(init_var.B_maxDiscWeightEnd);
              black_maxDisc_value.setText(temp);
              temp=Integer.toString(init_var.B_mobilityWeigthEnd);
              black_mobility_value.setText(temp);
              temp=Integer.toString(init_var.B_stableDiscWeightEnd);
              black_stability_value.setText(temp);
              temp=Integer.toString(init_var.B_frontierWeightEnd);
              black_frontier_value.setText(temp);   
          }
            
            options_area.revalidate();
            options_area.repaint();
            options_area.paintImmediately(0,0,260,340);
     
      }
      
      if(e.getSource()==white_stage_combo)
      {
          String temp;
          if (white_stage_combo.getSelectedIndex()==0)
          {
              temp=Integer.toString(init_var.W_maxDiscWeightEarly);
              white_maxDisc_value.setText(temp);
              temp=Integer.toString(init_var.W_mobilityWeigthEarly);
              white_mobility_value.setText(temp);
              temp=Integer.toString(init_var.W_stableDiscWeightEarly);
              white_stability_value.setText(temp);
              temp=Integer.toString(init_var.W_frontierWeightEarly);
              white_frontier_value.setText(temp);   
          }
          
           if (white_stage_combo.getSelectedIndex()==1)
          {
              temp=Integer.toString(init_var.W_maxDiscWeightMid);
              white_maxDisc_value.setText(temp);
              temp=Integer.toString(init_var.W_mobilityWeigthMid);
              white_mobility_value.setText(temp);
              temp=Integer.toString(init_var.W_stableDiscWeightMid);
              white_stability_value.setText(temp);
              temp=Integer.toString(init_var.W_frontierWeightMid);
              white_frontier_value.setText(temp);   
          }
           
            if (white_stage_combo.getSelectedIndex()==2)
          {
              temp=Integer.toString(init_var.W_maxDiscWeightEnd);
              white_maxDisc_value.setText(temp);
              temp=Integer.toString(init_var.W_mobilityWeigthEnd);
              white_mobility_value.setText(temp);
              temp=Integer.toString(init_var.W_stableDiscWeightEnd);
              white_stability_value.setText(temp);
              temp=Integer.toString(init_var.W_frontierWeightEnd);
              white_frontier_value.setText(temp);   
          }
            
            
            options_area.revalidate();
            options_area.repaint();
            options_area.paintImmediately(0,0,260,340);
     
      }

     
    if(e.getSource()==black_done)

     {
         if(black_stage_combo.getSelectedIndex()==0)
         {
            init_var.B_maxDiscWeightEarly = Integer.parseInt(black_maxDisc_value.getText());
            init_var.B_mobilityWeigthEarly = Integer.parseInt(black_mobility_value.getText());
            init_var.B_stableDiscWeightEarly = Integer.parseInt(black_stability_value.getText());
            init_var.B_frontierWeightEarly = Integer.parseInt(black_frontier_value.getText());

            
         }
         if(black_stage_combo.getSelectedIndex()==1)
         {
            init_var.B_maxDiscWeightMid = Integer.parseInt(black_maxDisc_value.getText());
            init_var.B_mobilityWeigthMid = Integer.parseInt(black_mobility_value.getText());
            init_var.B_stableDiscWeightMid = Integer.parseInt(black_stability_value.getText());
            init_var.B_frontierWeightMid = Integer.parseInt(black_frontier_value.getText());
             
         }
         if(black_stage_combo.getSelectedIndex()==2)
         {
            init_var.B_maxDiscWeightEnd = Integer.parseInt(black_maxDisc_value.getText());
            init_var.B_mobilityWeigthEnd = Integer.parseInt(black_mobility_value.getText());
            init_var.B_stableDiscWeightEnd = Integer.parseInt(black_stability_value.getText());
            init_var.B_frontierWeightEnd= Integer.parseInt(black_frontier_value.getText());
     
         }
     }
    
    if(e.getSource()==white_done)
     {
         if(white_stage_combo.getSelectedIndex()==0)
         {
            init_var.W_maxDiscWeightEarly = Integer.parseInt(white_maxDisc_value.getText());
            init_var.W_mobilityWeigthEarly = Integer.parseInt(white_mobility_value.getText());
            init_var.W_stableDiscWeightEarly = Integer.parseInt(white_stability_value.getText());
            init_var.W_frontierWeightEarly = Integer.parseInt(white_frontier_value.getText());
            
            
         }
         if(white_stage_combo.getSelectedIndex()==1)
         {
            init_var.W_maxDiscWeightMid = Integer.parseInt(white_maxDisc_value.getText());
            init_var.W_mobilityWeigthMid = Integer.parseInt(white_mobility_value.getText());
            init_var.W_stableDiscWeightMid = Integer.parseInt(white_stability_value.getText());
            init_var.W_frontierWeightMid = Integer.parseInt(white_frontier_value.getText());
             
         }
         if(white_stage_combo.getSelectedIndex()==2)
         {
            init_var.W_maxDiscWeightEnd = Integer.parseInt(white_maxDisc_value.getText());
            init_var.W_mobilityWeigthEnd = Integer.parseInt(white_mobility_value.getText());
            init_var.W_stableDiscWeightEnd = Integer.parseInt(white_stability_value.getText());
            init_var.W_frontierWeightEnd= Integer.parseInt(white_frontier_value.getText());
     
         }
     }
}
    
    public void pushToScreen(Board b)
           
    {
      
        game_area.removeAll();     
        board_column = new JButton[8];
        String[] board_column_value = {"1","2", "3", "4", "5","6","7","8"};
        board_row = new JButton[8];
        String[] board_row_value = {"A","B", "C", "D", "E","F","G","H"};
		
		for ( int i=0; i<8; i++)
		{
			board_column[i] = new JButton();
			board_column[i].setBounds(200,(i+2)*48,40,48);
			board_column[i].setBackground(Color.darkGray);
			board_column[i].setText(board_column_value[i]);
			board_column[i].setEnabled(false);
			board_column[i].setForeground(Color.white);
			board_column[i].setFont(new Font("Times New Roman",Font.CENTER_BASELINE, 8));
			board_column[i].setOpaque(true);
			game_area.add(board_column[i]);
          
		}
      
      for ( int i=0; i<8; i++)
      {
        board_row[i] = new JButton();
        board_row[i].setBounds(48*(i+5),55,48,40);
        board_row[i].setBackground(Color.darkGray);
        board_row[i].setText(board_row_value[i]);
        board_row[i].setEnabled(false);
        board_row[i].setForeground(Color.white);
        board_row[i].setFont(new Font("Times New Roman",Font.CENTER_BASELINE, 8));
        board_row[i].setOpaque(true);
        game_area.add(board_row[i]);
        
      }


        float[] hsb = Color.RGBtoHSB(0, 102, 0, null);

        ImageIcon black_image = new ImageIcon("black_fin.jpg");
        ImageIcon white_image = new ImageIcon("white_fin.jpg");
        ImageIcon candidate_image = new ImageIcon("candidate.jpg");
        b.markCandidates(b.playerTurn);
         
         board_area=new JButton[8][8];
         for ( int i=0; i<8; i++){
          for (int  j=0; j<8 ; j++){
            if(b.BoardA2D[i][j].candidateB00==true)
                board_area[i][j] = new JButton(candidate_image);
             else
                 board_area[i][j] = new JButton();
            if(b.BoardA2D[i][j].cellEnu==-1)
                board_area[i][j] = new JButton(black_image);
            else if (b.BoardA2D[i][j].cellEnu==1)
                board_area[i][j] = new JButton(white_image);
	
             board_area[i][j].setBounds((j+5)*48,(i+2)*48,48,48);
             board_area[i][j].setBackground(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
             
             board_area[i][j].setOpaque(true);
            
             game_area.add(board_area[i][j]);
			 board_area[i][j].addActionListener(this);
            }
        }
            game_area.revalidate();
            game_area.repaint();
            game_area.paintImmediately(0,0,800,500);
    }
    
    public void updateTable(gamePlay b)
    {   
        
        if((b.isOpeningB00==true) || (table_indicator==true)){
            position_table.setValueAt(b.gameHistory.substring(game_history_count, game_history_count+2), turn_count, 2);
            if(b.gameBoard.playerTurn==true)
            {
            position_table.setValueAt("Black", turn_count, 1);
            black_time_num.setText(Long.toString(execute_time+Long.parseLong(black_time_num.getText(),10)));
            }
            else
            {
            position_table.setValueAt("White", turn_count, 1); 
            white_time_num.setText(Long.toString(execute_time+Long.parseLong(white_time_num.getText(),10)));
            }
             position_table.setValueAt(Integer.toString(turn_count+1), turn_count, 0);
             options_area.revalidate();
             options_area.repaint();
             options_area.paintImmediately(200, 340, 200, 250);
             
             start_panel.revalidate();
             start_panel.repaint();
             start_panel.paintImmediately(280, 80, 200, 100);
             
             turn_count++;
             game_history_count= game_history_count +2;
		}
        else
        {
            table_indicator =true;
        }        
    }
    
    
    public void delayFunction()
    {
        long timeout = 100000000;
 
       for (long i=0;i<timeout;i++)
       {
           i++;
       }
    } 
    
    public void displayNumOfDiscs()
    {
        black_num_discs=0;
        white_num_discs =0;
          for (int i=0; i<8; i++)
          {  
              for (int j=0;j<8;j++)

              {
                  if(othelloGamePlay.gameBoard.BoardA2D[i][j].cellEnu==-1)
                      black_num_discs++;
                  else if (othelloGamePlay.gameBoard.BoardA2D[i][j].cellEnu==1)
                      white_num_discs++;
              }     
                  
          }
          black_disc_num.setText(Integer.toString(black_num_discs));
          white_disc_num.setText(Integer.toString(white_num_discs));
        
          start_panel.revalidate();
          start_panel.repaint();
          start_panel.paintImmediately(280,0,200,100);
 
    }
}
