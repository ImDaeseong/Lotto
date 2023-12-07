using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SQLite;
using System.IO;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        private bool bFirst = false;

        private SQLiteConnection _connection;

        private List<Lott> mlist = new List<Lott>();

        private void createdatabaseDaeseong()
        {
            String databaseName = "LottoDB.db";
            if (File.Exists(databaseName))
            {
                _connection = new SQLiteConnection("Data Source=" + databaseName);
                _connection.Open();
            }
            else
            {
                SQLiteConnection.CreateFile(databaseName);
                var _connection = new SQLiteConnection("Data Source=" + databaseName);
                _connection.Open();
            }
        }

        private void createTableLotto()
        {
            try
            {
                string query = "CREATE TABLE Lotto ( " +
                    "rIndex int," +
                    "Date varchar(20)," +
                    "Part1 int," +
                    "Part2 int," +
                    "Part3 int," +
                    "Part4 int," +
                    "Part5 int," +
                    "Part6 int," +
                    "Bonus int);";
                SQLiteCommand cmd = new SQLiteCommand(query, _connection);
                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message.ToString());
            }
        }

        private void insertLotto(string query)
        {
            try
            {
                SQLiteCommand com = new SQLiteCommand(query, _connection);
                com.ExecuteNonQuery();
            }
            catch
            {
            }
        }

        private SQLiteDataReader reader(string query)
        {
            SQLiteCommand com = new SQLiteCommand(query, _connection);
            return com.ExecuteReader();
        }

        private void LoadLotto()
        {
            try
            {
                SQLiteDataReader r = reader("SELECT * FROM Lotto ORDER BY rIndex DESC");
                while (r.Read())
                {
                    String s1 = r["rIndex"].ToString();
                    String s2 = r["Date"].ToString();
                    String s3 = r["Part1"].ToString();
                    String s4 = r["Part2"].ToString();
                    String s5 = r["Part3"].ToString();
                    String s6 = r["Part4"].ToString();
                    String s7 = r["Part5"].ToString();
                    String s8 = r["Part6"].ToString();
                    String s9 = r["Bonus"].ToString();
                    
                    //Console.WriteLine(s1, s2, s3, s4, s5, s6, s7, s8, s9 );
                    ListViewItem item = new ListViewItem();
                    item.Text = "";
                    item.SubItems.Add(s1);
                    item.SubItems.Add(s2);
                    item.SubItems.Add(s3);
                    item.SubItems.Add(s4);
                    item.SubItems.Add(s5);
                    item.SubItems.Add(s6);
                    item.SubItems.Add(s7);
                    item.SubItems.Add(s8);
                    item.SubItems.Add(s9);
                    lstView.Items.Add(item);
                }
            }
            catch
            {
            }

        }

        private string NullVal(object src, string Value)
        {
            if (src != null)
                return src.ToString();
            return Value;
        }

        private string QStr(string sValue)
        {
            return "'" + sValue.Replace("'", "''") + "'";
        }

        private void ReadFile()
        {
            StreamReader reader = new StreamReader("Lotto.txt");
            using (reader)
            {
                string rIndex;
                string Date;
                string Part1;
                string Part2;
                string Part3;
                string Part4;
                string Part5;
                string Part6;
                string Bonus;
                                
                string line = reader.ReadLine();
                while (line != null)
                {
                    string[] value = line.Split('|');
                    rIndex = value[0];

                    try
                    {
                        Date = NullVal(value[1], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Date = ""; }

                    try
                    {

                        Part1 = NullVal(value[2], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Part1 = ""; }

                    try
                    {
                        Part2 = NullVal(value[3], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Part2 = ""; }

                    try
                    {
                        Part3 = NullVal(value[4], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Part3 = ""; }

                    try
                    {
                        Part4 = NullVal(value[5], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Part4 = ""; }

                    try
                    {
                        Part5 = NullVal(value[6], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Part5 = ""; }

                    try
                    {
                        Part6 = NullVal(value[7], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Part6 = ""; }

                    try
                    {
                        Bonus = NullVal(value[8], "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
                    }
                    catch { Bonus = ""; }
                                       
                    string query = string.Format("INSERT INTO Lotto (rIndex, Date, Part1, Part2, Part3, Part4, Part5, Part6, Bonus) VALUES ({0},{1},{2},{3},{4},{5},{6},{7},{8});", rIndex, QStr(Date), Part1, Part2, Part3, Part4, Part5, Part6, Bonus);
                    insertLotto(query);

                    line = reader.ReadLine();
                }
            }
        }


        public Form1()
        {
            InitializeComponent();

            lstView.View = View.Details;
            lstView.GridLines = true;
            lstView.FullRowSelect = true;
            lstView.HeaderStyle = ColumnHeaderStyle.Clickable;
            lstView.CheckBoxes = true;
            lstView.OwnerDraw = true;

            lstView.Columns.Add("", 25, HorizontalAlignment.Left);
            lstView.Columns.Add("회차", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("추첨일", 100, HorizontalAlignment.Center);
            lstView.Columns.Add("1 당첨번호", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("2 당첨번호", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("3 당첨번호", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("4 당첨번호", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("5 당첨번호", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("6 당첨번호", 70, HorizontalAlignment.Center);
            lstView.Columns.Add("보너스", 70, HorizontalAlignment.Center);

            createdatabaseDaeseong();

            //처음 DB 생성후 데이터 insert(최초 사용시만 사용)
            if (bFirst)
            {
                createTableLotto();
                ReadFile();
            }

            LoadLotto();
        }

        private void InitChkBox()
        {
            for (int i = 0; i < lstView.Items.Count; i++)
                lstView.Items[i].Checked = false;
        }

        private void lstView_DrawColumnHeader(object sender, DrawListViewColumnHeaderEventArgs e)
        {
            if (e.ColumnIndex == 0)
            {
                e.DrawBackground();
                bool value = false;
                try
                {
                    value = Convert.ToBoolean(e.Header.Tag);
                }
                catch (Exception)
                {
                }
                CheckBoxRenderer.DrawCheckBox(e.Graphics,
                    new Point(e.Bounds.Left + 4, e.Bounds.Top + 4),
                    value ? System.Windows.Forms.VisualStyles.CheckBoxState.CheckedNormal :
                    System.Windows.Forms.VisualStyles.CheckBoxState.UncheckedNormal);
            }
            else
            {
                e.DrawDefault = true;
            }
        }

        private void lstView_DrawItem(object sender, DrawListViewItemEventArgs e)
        {
            e.DrawDefault = true;
        }

        private void lstView_DrawSubItem(object sender, DrawListViewSubItemEventArgs e)
        {
            e.DrawDefault = true;
        }

        private void lstView_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (lstView.SelectedItems.Count > 0)
            {
                string num = lstView.SelectedItems[0].SubItems[1].Text.ToString();
                string Date = lstView.SelectedItems[0].SubItems[2].Text.ToString(); 
                string part1 = lstView.SelectedItems[0].SubItems[3].Text.ToString();
                string part2 = lstView.SelectedItems[0].SubItems[4].Text.ToString();
                string part3 = lstView.SelectedItems[0].SubItems[5].Text.ToString();
                string part4 = lstView.SelectedItems[0].SubItems[6].Text.ToString();
                string part5 = lstView.SelectedItems[0].SubItems[7].Text.ToString();
                string part6 = lstView.SelectedItems[0].SubItems[8].Text.ToString();
                string Bonus = lstView.SelectedItems[0].SubItems[9].Text.ToString();

                txtrNum.Text = num;
                txtDate.Text = Date;
                txtPart1.Text = part1;
                txtPart2.Text = part2;
                txtPart3.Text = part3;
                txtPart4.Text = part4;
                txtPart5.Text = part5;
                txtPart6.Text = part6;
                txtBonus.Text = Bonus;
            }
            else
            {
                txtrNum.Text = "";
                txtDate.Text = "";
                txtPart1.Text = "";
                txtPart2.Text = "";
                txtPart3.Text = "";
                txtPart4.Text = "";
                txtPart5.Text = "";
                txtPart6.Text = "";
                txtBonus.Text = "";
            }
        }

        private void lstView_ColumnClick(object sender, ColumnClickEventArgs e)
        {
            if (e.Column == 0)
            {
                bool value = false;
                try
                {
                    value = Convert.ToBoolean(lstView.Columns[e.Column].Tag);
                }
                catch (Exception)
                {
                }
                lstView.Columns[e.Column].Tag = !value;
                foreach (ListViewItem item in lstView.Items)
                    item.Checked = !value;

                lstView.Invalidate();
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if( txtrNum.Text == "")  return;
            if( txtDate.Text == "")  return;
            if( txtPart1.Text == "") return;
            if( txtPart2.Text == "") return;
            if( txtPart3.Text == "") return;
            if( txtPart4.Text == "") return;
            if( txtPart5.Text == "") return;
            if( txtPart6.Text == "") return;
            if( txtBonus.Text == "") return;
            
            string rIndex;
            string Date;
            string Part1;
            string Part2;
            string Part3;
            string Part4;
            string Part5;
            string Part6;
            string Bonus;

            try
            {

                rIndex = NullVal(txtrNum.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { rIndex = ""; }

            try
            {
                Date = NullVal(txtDate.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Date = ""; }

            try
            {
                Part1 = NullVal(txtPart1.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Part1 = ""; }

            try
            {
                Part2 = NullVal(txtPart2.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Part2 = ""; }

            try
            {
                Part3 = NullVal(txtPart3.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Part3 = ""; }

            try
            {
                Part4 = NullVal(txtPart4.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Part4 = ""; }

            try
            {
                Part5 = NullVal(txtPart5.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Part5 = ""; }

            try
            {
                Part6 = NullVal(txtPart6.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Part6 = ""; }

            try
            {
                Bonus = NullVal(txtBonus.Text, "").Replace("\"", "").Replace("\r", "").Replace("\n", "").Replace("\t", "").Trim();
            }
            catch { Bonus = ""; }

            string query = string.Format("INSERT INTO Lotto (rIndex, Date, Part1, Part2, Part3, Part4, Part5, Part6, Bonus) VALUES ({0},{1},{2},{3},{4},{5},{6},{7},{8});", rIndex, QStr(Date), Part1, Part2, Part3, Part4, Part5, Part6, Bonus);
            insertLotto(query);

            txtrNum.Text = "";
            txtPart1.Text = "";
            txtPart2.Text = "";
            txtPart3.Text = "";
            txtPart4.Text = "";
            txtPart5.Text = "";
            txtPart6.Text = "";
        }

        //당첨번호 1 
        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                //TOP 1
                SQLiteDataReader r = reader("SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc limit 1");
                
                //전체
                //SQLiteDataReader r = reader("SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc");
                while (r.Read())
                {
                    String s1 = r["Part1"].ToString();
                    String s2 = r["cnt"].ToString();
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2 );
                }               
            }
            catch
            {
            }

        }

        //당첨번호 2 
        private void button2_Click(object sender, EventArgs e)
        {
            try
            {                
                //TOP 1
                SQLiteDataReader r = reader("SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 1");

                //전체
                //SQLiteDataReader r = reader("SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc");
                while (r.Read())
                {
                    String s1 = r["Part2"].ToString();
                    String s2 = r["cnt"].ToString();
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                }
            }
            catch
            {
            }
        }

        //당첨번호 3
        private void button3_Click(object sender, EventArgs e)
        {
            try
            {               
                //TOP 1
                SQLiteDataReader r = reader("SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 1");

                //전체
                //SQLiteDataReader r = reader("SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc");
                while (r.Read())
                {
                    String s1 = r["Part3"].ToString();
                    String s2 = r["cnt"].ToString();
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                }
            }
            catch
            {
            }
        }

        //당첨번호 4
        private void button4_Click(object sender, EventArgs e)
        {
            try
            {                
                //TOP 1
                SQLiteDataReader r = reader("SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 1");

                //전체
                //SQLiteDataReader r = reader("SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc");
                while (r.Read())
                {
                    String s1 = r["Part4"].ToString();
                    String s2 = r["cnt"].ToString();
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                }
            }
            catch
            {
            }
        }

        //당첨번호 5
        private void button5_Click(object sender, EventArgs e)
        {
            try
            {                
                //TOP 1
                SQLiteDataReader r = reader("SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 1");

                //전체
                //SQLiteDataReader r = reader("SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc");
                while (r.Read())
                {
                    String s1 = r["Part5"].ToString();
                    String s2 = r["cnt"].ToString();
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                }
            }
            catch
            {
            }
        }

        //당첨번호 6 
        private void button6_Click(object sender, EventArgs e)
        {
            try
            {                
                //TOP 1
                SQLiteDataReader r = reader("SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 1");

                //전체
                //SQLiteDataReader r = reader("SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc");
                while (r.Read())
                {
                    String s1 = r["Part6"].ToString();
                    String s2 = r["cnt"].ToString();
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                }
            }
            catch
            {
            }
        }

        //당첨번호 1 부터 6 까지 1개만
        private void button7_Click(object sender, EventArgs e)
        {
            try
            {               
                SQLiteDataReader r = reader(" SELECT n1, cnt1 ,n2, cnt2 ,n3, cnt3, n4, cnt4, n5, cnt5, n6, cnt6 from " +
                                            " (SELECT Part1 as n1, count(*) as cnt1 FROM Lotto GROUP BY Part1 order by cnt1 desc limit 1), " +
                                            " (SELECT Part2 as n2, count(*) as cnt2 FROM Lotto GROUP BY Part2 order by cnt2 desc limit 1), " +
                                            " (SELECT Part3 as n3, count(*) as cnt3 FROM Lotto GROUP BY Part3 order by cnt3 desc limit 1), " +
                                            " (SELECT Part4 as n4, count(*) as cnt4 FROM Lotto GROUP BY Part4 order by cnt4 desc limit 1), " +
                                            " (SELECT Part5 as n5, count(*) as cnt5 FROM Lotto GROUP BY Part5 order by cnt5 desc limit 1), " +
                                            " (SELECT Part6 as n6, count(*) as cnt6 FROM Lotto GROUP BY Part6 order by cnt6 desc limit 1) ");
                
                while (r.Read())
                {
                    String n1 = r["n1"].ToString();
                    String cnt1 = r["cnt1"].ToString();

                    String n2 = r["n2"].ToString();
                    String cnt2 = r["cnt2"].ToString();

                    String n3 = r["n3"].ToString();
                    String cnt3 = r["cnt3"].ToString();

                    String n4 = r["n4"].ToString();
                    String cnt4 = r["cnt4"].ToString();

                    String n5 = r["n5"].ToString();
                    String cnt5 = r["cnt5"].ToString();

                    String n6 = r["n6"].ToString();
                    String cnt6 = r["cnt6"].ToString();
                    
                    Console.WriteLine("1번호:" + n1 + " 개수:" + cnt1);
                    Console.WriteLine("2번호:" + n2 + " 개수:" + cnt2);
                    Console.WriteLine("3번호:" + n3 + " 개수:" + cnt3);
                    Console.WriteLine("4번호:" + n4 + " 개수:" + cnt4);
                    Console.WriteLine("5번호:" + n5 + " 개수:" + cnt5);
                    Console.WriteLine("6번호:" + n6 + " 개수:" + cnt6);
                }
            }
            catch
            {
            }

        }

        //당첨번호 1 ~ 6 번중 전체 가장 많은 개수 체크
        private void button8_Click(object sender, EventArgs e)
        {
            try
            {
                //TOP 10
                SQLiteDataReader r1 = reader("SELECT Part1, count(*) as cnt FROM Lotto GROUP BY Part1 order by cnt desc limit 10");
                while (r1.Read())
                {
                    String s1 = r1["Part1"].ToString();
                    String s2 = r1["cnt"].ToString();
                    Lott item = new Lott(Convert.ToInt32(s1), Convert.ToInt32(s2));
                    mlist.Add(item);
                }

                //TOP 10
                SQLiteDataReader r2 = reader("SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 10");
                while (r2.Read())
                {
                    String s1 = r2["Part2"].ToString();
                    String s2 = r2["cnt"].ToString();
                    Lott item = new Lott(Convert.ToInt32(s1), Convert.ToInt32(s2));
                    mlist.Add(item);
                }

                //TOP 10
                SQLiteDataReader r3 = reader("SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 10");
                while (r3.Read())
                {
                    String s1 = r3["Part3"].ToString();
                    String s2 = r3["cnt"].ToString();
                    Lott item = new Lott(Convert.ToInt32(s1), Convert.ToInt32(s2));
                    mlist.Add(item);
                }

                //TOP 10
                SQLiteDataReader r4 = reader("SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 10");
                while (r4.Read())
                {
                    String s1 = r4["Part4"].ToString();
                    String s2 = r4["cnt"].ToString();
                    Lott item = new Lott(Convert.ToInt32(s1), Convert.ToInt32(s2));
                    mlist.Add(item);
                }

                //TOP 10
                SQLiteDataReader r5 = reader("SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 10");
                while (r5.Read())
                {
                    String s1 = r5["Part5"].ToString();
                    String s2 = r5["cnt"].ToString();
                    Lott item = new Lott(Convert.ToInt32(s1), Convert.ToInt32(s2));
                    mlist.Add(item);
                }

                //TOP 10
                SQLiteDataReader r6 = reader("SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 10");
                while (r6.Read())
                {
                    String s1 = r6["Part6"].ToString();
                    String s2 = r6["cnt"].ToString();
                    Lott item = new Lott(Convert.ToInt32(s1), Convert.ToInt32(s2));
                    mlist.Add(item);
                }
                
                mlist.Sort((Lott x, Lott y) => y.getCount().CompareTo(x.getCount()));
                //mlist.Sort((Lott x, Lott y) => x.getCount().CompareTo(y.getCount()));

                foreach (Lott obj in mlist)
                {
                    Console.WriteLine("번호:" + obj.getNumber() + " 개수:" + obj.getCount());
                }

            }
            catch
            {
            }            
        }

    }

    public class Lott
    {
        private int nNumber;
        private int nCount;

        public Lott(int nNumber, int nCount)
        {
            this.nNumber = nNumber;
            this.nCount = nCount;
        }

        public int getNumber()
        {
            return nNumber;
        }

        public int getCount()
        {
            return nCount;
        }
    }
}
