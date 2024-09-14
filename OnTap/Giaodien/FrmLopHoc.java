

import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.GiaoVien_dao;
import dao.LopHoc_dao;
import entity.GiaoVien_entity;
import entity.LopHoc_entity;



public class FrmLopHoc extends JFrame  implements ActionListener, MouseListener{
	 
	private JTextField txtMaLop ;
	private JTextField txtTenLop;
	private JTextField txtSiSo;
	private JComboBox<String> cboGVCN;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimGV,btnTimLop;
	private DefaultTableModel dataModel;
	private JTable table;
	private LopHoc_dao lopHoc_dao;
	private ArrayList<LopHoc_entity> dsLopHoc;
	private GiaoVien_dao giaoVien_dao;
	private ArrayList<GiaoVien_entity> dsGiaoVien;
	private JButton btnLoc;
	private JButton btnKetThuc;

	public FrmLopHoc() {
		setTitle("Thông tin lớp h�?c");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Box b, b1, b2, b3, b4, b44, b5, b6, b7;
		//Dùng Box layout
		add(b = Box.createVerticalBox()); //Box theo chi�?u d�?c
		b.add(Box.createVerticalStrut(10)); //Tạo khoảng cách theo chi�?u d�?c
		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); //b1 -> b7: Box theo chi�?u ngang
		b.add(b2 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b3 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b4 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b44 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b5 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b6 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b7 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 

		JLabel lblTieuDe, lblMaLop, lblTenLop, lblGVCN, lblSiSo;
		b1.add(lblTieuDe = new JLabel("THÔNG TIN LỚP HỌC", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 26));

		b2.add(lblMaLop = new JLabel("Mã lớp: ", JLabel.RIGHT)); b2.add(txtMaLop = new JTextField());
		b3.add(lblTenLop = new JLabel("Tên lớp: ", JLabel.RIGHT)); b3.add(txtTenLop = new JTextField());
		b4.add(lblGVCN = new JLabel("Giáo viên chủ nhiệm: ", JLabel.RIGHT)); 
		b4.add(cboGVCN = new JComboBox<String>());
		cboGVCN.setEditable(false);
		b44.add(lblSiSo = new JLabel("Sỉ số : ", JLabel.RIGHT)); b44.add(txtSiSo = new JTextField());
		
		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblSiSo.setPreferredSize(lblGVCN.getPreferredSize());
		
		b5.add(btnThem = new JButton("Thêm"));
		b5.add(btnLuu= new JButton("Lưu"));
		b5.add(btnSua = new JButton("Sửa"));
		b5.add(btnXoa = new JButton("Xóa"));
		b5.add(btnTimGV = new JButton("Tìm theo mã giáo viên"));
		b5.add(btnTimLop = new JButton("Tìm theo mã lớp"));
		b5.add(btnLoc = new JButton("Lọc theo giáo viên"));
		b5.add(btnKetThuc = new JButton("Kết thúc"));

		String[] headers = {"Mã lớp", "Tên lớp", "Giáo viên CN", "Sỉ số"};
		dataModel = new DefaultTableModel(headers , 0);
		JScrollPane scroll;
		b6.add(scroll = new JScrollPane(table = new JTable(dataModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp h�?c"));

		b7.add(Box.createHorizontalStrut(600));
		
		
		btnThem.addActionListener(e -> addAction());
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(e -> deleteAction());
		btnSua.addActionListener(e -> updateAction());
		btnTimGV.addActionListener(e -> timGiaoVienAction());
		btnTimLop.addActionListener(e -> timLopAction());
		btnKetThuc.addActionListener(e -> ketThucAction());
		btnLoc.addActionListener(e -> locAction());
		table.addMouseListener(this);
	
		
		//Khi chương trình chạy, nạp toàn bộ danh sách lớp h�?c lên bảng
		readDBToTable();
		updateCBGVCN();
	
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnLuu)){
			
		}
	
 }
	private void xoaRongTextfields() {
		txtMaLop.setText("");
		txtTenLop.setText("");
		cboGVCN.setSelectedItem(null);
		txtSiSo.setText("");
		txtMaLop.requestFocus();
	}
	
	private void readDBToTable() {
		lopHoc_dao = new LopHoc_dao();
		dsLopHoc = lopHoc_dao.getDSLopHoc();
		giaoVien_dao = new GiaoVien_dao();
		dsGiaoVien = giaoVien_dao.getDSGiaoVien();
		for (LopHoc_entity lh : dsLopHoc) {
			String tenGV = lopHoc_dao.findTenGV(lh.getMaLop());
			dataModel.addRow(new Object[] {lh.getMaLop(), lh.getTenLop(), tenGV, lh.getSiSo()});
		}
	}
	
	private void updateCBGVCN() {
		GiaoVien_dao giaoVien_dao = new GiaoVien_dao();
		ArrayList<GiaoVien_entity> dsGV = giaoVien_dao.getDSGiaoVien();
		ArrayList<String> list = new ArrayList<String>();
		for (GiaoVien_entity GV : dsGV) {
			if(!list.contains(GV.getTenGiaoVien())) list.add(GV.getTenGiaoVien());
		}
		String []items = list.toArray(new String[0]);
		cboGVCN.setModel(new DefaultComboBoxModel<String>(items));
	}
	
	public LopHoc_entity LayThongTinTuTF() {
		String maLop = txtMaLop.getText().trim();
		String tenLop = txtTenLop.getText().trim();
		String tenGiaoVien = (String) cboGVCN.getSelectedItem();
		String maGiaoVien = giaoVien_dao.findMaGV(tenGiaoVien);
		int siSo = Integer.parseInt(txtSiSo.getText().trim());
		LopHoc_entity lh = new LopHoc_entity(maLop, tenLop, maGiaoVien, siSo);
		return lh;
	}
	
	public void reload() {
		dataModel.setRowCount(0);
		readDBToTable();
		updateCBGVCN();
		xoaRongTextfields();
	}
	
	private void addAction() {
		if(validData()) {
			LopHoc_entity lh = LayThongTinTuTF();
			try {
				lopHoc_dao.create(lh);
				reload();
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(this, "Trung ma lop!");
			}
		} else JOptionPane.showMessageDialog(this, "Thong tin khong hop le!");
	}
	
	private void deleteAction() {
		int row = table.getSelectedRow();
		if(row != -1) {
			if(JOptionPane.showConfirmDialog(this, "Ban co chac chan xoa?", "Canh bao", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(lopHoc_dao.delete(dataModel.getValueAt(row, 0).toString())) reload();
			}
		}
	}
	
	private void updateAction() {
		int row = table.getSelectedRow();
		if(row != -1) {
			if(validData()) {
				LopHoc_entity lh = LayThongTinTuTF();
				if(lopHoc_dao.update(lh)) reload();
			}
		}	
	}
	
	private void locAction() {
		String tenGiaoVien = (String) cboGVCN.getSelectedItem();
		ArrayList<LopHoc_entity> dsMoi = lopHoc_dao.locTheoTenGV(tenGiaoVien);
//		String maGiaoVien = giaoVien_dao.findMaGV(tenGiaoVien);
		dataModel.setRowCount(0);
		for (LopHoc_entity lh : dsMoi) {
				String tenGV = lopHoc_dao.findTenGV(lh.getMaLop());
				dataModel.addRow(new Object[] {lh.getMaLop(), lh.getTenLop(), tenGV, lh.getSiSo()});
		}
	}
	
	private void ketThucAction() {
		System.exit(0);
	}
	
	public boolean validData() {
		String maLop = txtMaLop.getText().trim();
		String tenLop = txtTenLop.getText().trim();
		int siSo = Integer.parseInt(txtSiSo.getText().trim());
		if(maLop.length() < 1) {
			JOptionPane.showMessageDialog(this, "Chua nhap ma lop!");
			txtMaLop.requestFocus();
			return false;
		}
		if(tenLop.length() < 1) {
			JOptionPane.showMessageDialog(this, "Chua nhap ten lop!");
			txtTenLop.requestFocus();
			return false;
		}
		if(siSo<1 || siSo > 120 || txtSiSo.getText().trim().length() < 1) {
			JOptionPane.showMessageDialog(this, "Si so khong hop le! (0<siSo<=120)");
			txtSiSo.requestFocus();
			return false;
		}
		
		return true;
	}
	
	public void timLopAction() {
		table.clearSelection();
		String maLop = JOptionPane.showInputDialog("Nhap ma lop can tim:");
		if(maLop != null && !maLop.isEmpty()) {
			LopHoc_entity lh = lopHoc_dao.getLopHocByMaLop(maLop);
			for(int i = 0; i < dataModel.getRowCount(); i++) {
				if(dataModel.getValueAt(i, 0).toString().equals(lh.getMaLop())) table.setRowSelectionInterval(i, i);
			}
		}
	}
	
	public void timGiaoVienAction() {
		table.clearSelection();
		String maGiaoVien = JOptionPane.showInputDialog("Nhap ma giao vien can tim:");
		if(maGiaoVien != null && !maGiaoVien.isEmpty()) {
			for(int i = 0; i < dataModel.getRowCount(); i++) {
				LopHoc_entity lh = lopHoc_dao.getLopHocByMaLop(dataModel.getValueAt(i, 0).toString());
				if(lh.getMaGiaoVien().trim().equals(maGiaoVien)) {
					table.addRowSelectionInterval(i, i);
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		if(row != -1) {
			txtMaLop.setText(dataModel.getValueAt(row, 0).toString());
			txtTenLop.setText(dataModel.getValueAt(row, 1).toString());
			cboGVCN.setSelectedItem(dataModel.getValueAt(row, 2).toString());
			txtSiSo.setText(dataModel.getValueAt(row, 3).toString());
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}
