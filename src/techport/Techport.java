/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package techport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.html.parser.Parser;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author ksmirnov
 */
public class Techport extends javax.swing.JFrame {

    /**
     * Creates new form Techport_parse
     */
    public Techport() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToggleButton1.setText("Parse!");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jTextField3.setText("D://");

        jLabel1.setText("Output Directory");

        jLabel2.setText("Port");

        jLabel3.setText("Server");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 317, Short.MAX_VALUE)
                        .addComponent(jToggleButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        Workbook book = new HSSFWorkbook();
        Sheet offers = book.createSheet(timeStamp);
        ArrayList<String> descs = new ArrayList<String>();
        ArrayList<String> conds = new ArrayList<String>();
        ArrayList<String> linx = new ArrayList<String>();
        Row offerstitle = offers.createRow(0);
        Cell title = offerstitle.createCell(0);
        title.setCellValue("Link");
        Row offerstitle1 = offers.getRow(0);
        Cell title1 = offerstitle1.createCell(1);
        title1.setCellValue("Price");
        Row offerstitle2 = offers.getRow(0);
        Cell title2 = offerstitle2.createCell(2);
        title2.setCellValue("Offer name");
        String pg = "https://www.techport.ru/";
        String fl = jTextField3.getText() + "/parse.xls";
        String srv = jTextField1.getText();
        String port = jTextField2.getText();

        try {
            List a = getLinksOnPage(pg);
            for (int m = 0; m < a.size(); m++) {
                if (a.get(m).toString().contains("products/audio-video-tv-tehnika/televizory/led")) {
                    for (int k = 1; k < 7; k++) {
                        try {
                            URL url = new URL(a.get(m).toString() + "?offset=" + (k * 28));
                            System.out.println(a.get(m).toString() + "?offset=" + (k * 28));
                            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(srv, Integer.valueOf(port))); // or whatever your proxy is
                            HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);

                            uc.connect();

                            String line = null;
                            StringBuffer tmp = new StringBuffer();
                            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
                            while ((line = in.readLine()) != null) {
                                tmp.append(line);
                            }
                            //System.out.println(Jsoup.parse(String.valueOf(tmp)).body().text());

                            try {
                                Elements description = Jsoup.parse(String.valueOf(tmp)).getElementsByClass("tcp-product__link");
                                descs.clear();
                                for (Element d : description) {
                                    if (d.toString().contains("televizory")) {
                                        descs.add(d.attr("href"));
                                    }
                                }
                                System.out.println(descs.size());
                                for (int i = 1; i <= descs.size(); i++) {
                                    Row offersrow = offers.createRow((k - 2) * descs.size() + i);
                                    Cell name = offersrow.createCell(0);
                                    name.setCellValue(descs.get(i - 1));
                                    offers.autoSizeColumn(0);
                                    try {
                                        Elements condition = Jsoup.connect("https://www.techport.ru" + descs.get(i - 1)).get().getElementsByClass("tcp-product-body__new-price tcp-product-body-set__new-price");
                                        conds.clear();
                                        for (Element c : condition) {
                                            conds.add(c.text());
                                            System.out.println(c.text());
                                        }
                                        for (int j = 1; j <= conds.size(); j++) {
                                            Row offersrow1 = offers.getRow((k - 2) * descs.size() + i);
                                            Cell name1 = offersrow1.createCell(1);
                                            name1.setCellValue(conds.get(j - 1));
                                            offers.autoSizeColumn(1);
                                            System.out.println((k - 2) * descs.size() + i);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        Elements links = Jsoup.connect("https://www.techport.ru" + descs.get(i - 1)).get().getElementsByClass("tcp-dashboard-header__h1");
                                        linx.clear();
                                        for (Element lk : links) {
                                            linx.add(lk.text());
                                        }
                                        for (int l = 1; l <= linx.size(); l++) {
                                            Row offersrow2 = offers.getRow((k - 2) * descs.size() + i);
                                            Cell name2 = offersrow2.createCell(2);
                                            name2.setCellValue(linx.get(l - 1));
                                            offers.autoSizeColumn(2);
                                        }

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            try {
                                FileOutputStream outputStream = new FileOutputStream(fl);
                                book.write(outputStream);
                                book.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } catch (MalformedURLException ex) {
                            Logger.getLogger(Techport.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Techport.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }

            }
        } catch (ParserException ex) {
            Logger.getLogger(Techport.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "File saved to: " + jTextField3.getText() + "/parse.xls");

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */


 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Techport().setVisible(true);
            }
        });
    }

    public static List<String> getLinksOnPage(final String url) throws ParserException {

        final List<String> result = new LinkedList<String>();

        final org.htmlparser.Parser htmlParser = new org.htmlparser.Parser(url);

        try {
            final NodeList tagNodeList = htmlParser.extractAllNodesThatMatch(new NodeClassFilter(LinkTag.class));
            for (int j = 0; j < tagNodeList.size(); j++) {
                final LinkTag loopLink = (LinkTag) tagNodeList.elementAt(j);
                final String loopLinkStr = loopLink.getLink();
                result.add(loopLinkStr);
            }
        } catch (ParserException e) {
            e.printStackTrace(); // TODO handle error
        }

        return result;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
