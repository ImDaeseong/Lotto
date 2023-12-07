using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Data.SQLite;
using System.IO;
using System.IO.Ports;
using System.Reflection;

namespace WpfApp1
{
    public partial class MainWindow : Window
    {

        private SQLiteConnection _connection;

        private void createdatabaseDaeseong()
        {
            string databaseName = "LottoDB.db";
            if (File.Exists(databaseName))
            {
                _connection = new SQLiteConnection("Data Source=" + databaseName);
                _connection.Open();
            }
            else
            {
                SQLiteConnection.CreateFile(databaseName);
                _connection = new SQLiteConnection("Data Source=" + databaseName);
                _connection.Open();
            }
        }

        private void createTableLotto()
        {
            try
            {
                string query = "CREATE TABLE IF NOT EXISTS Lotto ( " +
                    "rIndex INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Date TEXT," +
                    "Part1 INTEGER," +
                    "Part2 INTEGER," +
                    "Part3 INTEGER," +
                    "Part4 INTEGER," +
                    "Part5 INTEGER," +
                    "Part6 INTEGER," +
                    "Bonus INTEGER);";
                SQLiteCommand cmd = new SQLiteCommand(query, _connection);
                cmd.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message.ToString());
            }
        }

        private void insertLotto(SQLiteCommand cmd)
        {
            try
            {
                cmd.Connection = _connection;
                cmd.ExecuteNonQuery();
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
                List<LotteItem> lottoDataList = new List<LotteItem>();

                SQLiteDataReader r = reader("SELECT * FROM Lotto ORDER BY rIndex DESC");

                while (r.Read())
                {
                    int s1 = Convert.ToInt32(r["rIndex"]);
                    string s2 = r["Date"].ToString();
                    int s3 = Convert.ToInt32(r["Part1"]);
                    int s4 = Convert.ToInt32(r["Part2"]);
                    int s5 = Convert.ToInt32(r["Part3"]);
                    int s6 = Convert.ToInt32(r["Part4"]);
                    int s7 = Convert.ToInt32(r["Part5"]);
                    int s8 = Convert.ToInt32(r["Part6"]);
                    int s9 = Convert.ToInt32(r["Bonus"]);

                    LotteItem Item = new LotteItem
                    {
                        rIndex = s1,
                        Date = s2,
                        Part1 = s3,
                        Part2 = s4,
                        Part3 = s5,
                        Part4 = s6,
                        Part5 = s7,
                        Part6 = s8,
                        Bonus = s9
                    };

                    lottoDataList.Add(Item);
                }

                lstView.ItemsSource = lottoDataList;
            }
            catch
            {
            }
        }

        private string NullVal(object src, string Value)
        {
            return src?.ToString() ?? Value;
        }



        public MainWindow()
        {
            InitializeComponent();

            createdatabaseDaeseong();

            LoadLotto();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            lstView.SelectionChanged += LstView_SelectionChanged;
        }

        private void LstView_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            if (lstView.SelectedItem != null)
            {
                LotteItem selectedItem = (LotteItem)lstView.SelectedItem;
                int rIndex = selectedItem.rIndex;
                string sDate = selectedItem.Date;
                int sPart1 = selectedItem.Part1;
                int sPart2 = selectedItem.Part2;
                int sPart3 = selectedItem.Part3;
                int sPart4 = selectedItem.Part4;
                int sPart5 = selectedItem.Part5;
                int sPart6 = selectedItem.Part6;
                int sBonus = selectedItem.Bonus;

                //Console.WriteLine("rIndex:" + rIndex);
                //Console.WriteLine("sDate:" + sDate);
                //Console.WriteLine("sPart1:" + sPart1);
                //Console.WriteLine("sPart2:" + sPart2);
                //Console.WriteLine("sPart3:" + sPart3);
                //Console.WriteLine("sPart4:" + sPart4);
                //Console.WriteLine("sPart5:" + sPart5);
                //Console.WriteLine("sPart6:" + sPart6);
                //Console.WriteLine("sBonus:" + sBonus);

                txtrNum.Text = rIndex.ToString();
                txtDate.Text = sDate;
                txtPart1.Text = sPart1.ToString();
                txtPart2.Text = sPart2.ToString();
                txtPart3.Text = sPart3.ToString();
                txtPart4.Text = sPart4.ToString();
                txtPart5.Text = sPart5.ToString();
                txtPart6.Text = sPart6.ToString();
                txtBonus.Text = sBonus.ToString();
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


        //전체 체크박스 클릭
        private void CheckBox_All_Click(object sender, RoutedEventArgs e)
        {
            
        }

        //개별 체크박스 클릭
        private void CheckBox_Row_Click(object sender, RoutedEventArgs e)
        {
            
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtrNum.Text) ||
                string.IsNullOrWhiteSpace(txtDate.Text) ||
                string.IsNullOrWhiteSpace(txtPart1.Text) ||
                string.IsNullOrWhiteSpace(txtPart2.Text) ||
                string.IsNullOrWhiteSpace(txtPart3.Text) ||
                string.IsNullOrWhiteSpace(txtPart4.Text) ||
                string.IsNullOrWhiteSpace(txtPart5.Text) ||
                string.IsNullOrWhiteSpace(txtPart6.Text) ||
                string.IsNullOrWhiteSpace(txtBonus.Text))
            {
                return;
            }

            int rIndex;
            string Date;
            int Part1;
            int Part2;
            int Part3;
            int Part4;
            int Part5;
            int Part6;
            int Bonus;

            if (!int.TryParse(txtrNum.Text, out rIndex)) rIndex = 0;
            Date = NullVal(txtDate.Text, "");
            if (!int.TryParse(txtPart1.Text, out Part1)) Part1 = 0;
            if (!int.TryParse(txtPart2.Text, out Part2)) Part2 = 0;
            if (!int.TryParse(txtPart3.Text, out Part3)) Part3 = 0;
            if (!int.TryParse(txtPart4.Text, out Part4)) Part4 = 0;
            if (!int.TryParse(txtPart5.Text, out Part5)) Part5 = 0;
            if (!int.TryParse(txtPart6.Text, out Part6)) Part6 = 0;
            if (!int.TryParse(txtBonus.Text, out Bonus)) Bonus = 0;

            string query = "INSERT INTO Lotto (rIndex, Date, Part1, Part2, Part3, Part4, Part5, Part6, Bonus) VALUES (@rIndex, @Date, @Part1, @Part2, @Part3, @Part4, @Part5, @Part6, @Bonus)";

            using (SQLiteCommand cmd = new SQLiteCommand(query, _connection))
            {
                cmd.Parameters.AddWithValue("@rIndex", rIndex);
                cmd.Parameters.AddWithValue("@Date", Date);
                cmd.Parameters.AddWithValue("@Part1", Part1);
                cmd.Parameters.AddWithValue("@Part2", Part2);
                cmd.Parameters.AddWithValue("@Part3", Part3);
                cmd.Parameters.AddWithValue("@Part4", Part4);
                cmd.Parameters.AddWithValue("@Part5", Part5);
                cmd.Parameters.AddWithValue("@Part6", Part6);
                cmd.Parameters.AddWithValue("@Bonus", Bonus);

                insertLotto(cmd);
            }

            txtrNum.Text = "";
            txtPart1.Text = "";
            txtPart2.Text = "";
            txtPart3.Text = "";
            txtPart4.Text = "";
            txtPart5.Text = "";
            txtPart6.Text = "";
            txtBonus.Text = "";
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
                    int s1 = Convert.ToInt32(r["Part1"]);
                    int s2 = Convert.ToInt32(r["cnt"]);
                    Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

        }

        //당첨번호 2 
        private void button2_Click(object sender, EventArgs e)
        {
            try
            {
                //전체
                //using (SQLiteDataReader r = reader("SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc"))

                //TOP 1
                using (SQLiteDataReader r = reader("SELECT Part2, count(*) as cnt FROM Lotto GROUP BY Part2 order by cnt desc limit 1"))
                {
                    while (r.Read())
                    {
                        int s1 = Convert.ToInt32(r["Part2"]);
                        int s2 = Convert.ToInt32(r["cnt"]);
                        Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

        }

        //당첨번호 3
        private void button3_Click(object sender, EventArgs e)
        {
            try
            {
                //전체
                //using (SQLiteDataReader r = reader("SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc"))

                //TOP 1
                using (SQLiteDataReader r = reader("SELECT Part3, count(*) as cnt FROM Lotto GROUP BY Part3 order by cnt desc limit 1"))
                {
                     while (r.Read())
                    {
                        int s1 = Convert.ToInt32(r["Part3"]);
                        int s2 = Convert.ToInt32(r["cnt"]);
                        Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                    }
                }
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLiteException: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception: " + ex.Message);
            }

        }

        //당첨번호 4
        private void button4_Click(object sender, EventArgs e)
        {
            try
            {
                //전체
                //using (SQLiteDataReader r = reader("SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc"))

                //TOP 1
                using (SQLiteDataReader r = reader("SELECT Part4, count(*) as cnt FROM Lotto GROUP BY Part4 order by cnt desc limit 1"))
                {                   
                    while (r.Read())                    
                    {
                        int s1 = Convert.ToInt32(r["Part4"]);
                        int s2 = Convert.ToInt32(r["cnt"]);
                        Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                    }
                }
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLiteException: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception: " + ex.Message);
            }

        }

        //당첨번호 5
        private void button5_Click(object sender, EventArgs e)
        {
            try
            {
                //전체
                //using (SQLiteDataReader r = reader("SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc"))

                //TOP 1
                using (SQLiteDataReader r = reader("SELECT Part5, count(*) as cnt FROM Lotto GROUP BY Part5 order by cnt desc limit 1"))
                {                    
                    while (r.Read())
                    {
                        int s1 = Convert.ToInt32(r["Part5"]);
                        int s2 = Convert.ToInt32(r["cnt"]);
                        Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                    }
                }
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLiteException: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception: " + ex.Message);
            }

        }

        //당첨번호 6 
        private void button6_Click(object sender, EventArgs e)
        {
            try
            {
                //전체
                //using (SQLiteDataReader r = reader("SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc"))

                //TOP 1
                using (SQLiteDataReader r = reader("SELECT Part6, count(*) as cnt FROM Lotto GROUP BY Part6 order by cnt desc limit 1"))
                {
                    while (r.Read())
                    {
                        int s1 = Convert.ToInt32(r["Part6"]);
                        int s2 = Convert.ToInt32(r["cnt"]);
                        Console.WriteLine("번호:" + s1 + " 개수:" + s2);
                    }
                }
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLiteException: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception: " + ex.Message);
            }

        }

        //당첨번호 1 부터 6 까지 1개만
        private void button7_Click(object sender, EventArgs e)
        {
            try
            {
                using (SQLiteDataReader r = reader("SELECT n1, cnt1, n2, cnt2, n3, cnt3, n4, cnt4, n5, cnt5, n6, cnt6 FROM " +
                                                    "(SELECT Part1 as n1, count(*) as cnt1 FROM Lotto GROUP BY Part1 ORDER BY cnt1 DESC LIMIT 1), " +
                                                    "(SELECT Part2 as n2, count(*) as cnt2 FROM Lotto GROUP BY Part2 ORDER BY cnt2 DESC LIMIT 1), " +
                                                    "(SELECT Part3 as n3, count(*) as cnt3 FROM Lotto GROUP BY Part3 ORDER BY cnt3 DESC LIMIT 1), " +
                                                    "(SELECT Part4 as n4, count(*) as cnt4 FROM Lotto GROUP BY Part4 ORDER BY cnt4 DESC LIMIT 1), " +
                                                    "(SELECT Part5 as n5, count(*) as cnt5 FROM Lotto GROUP BY Part5 ORDER BY cnt5 DESC LIMIT 1), " +
                                                    "(SELECT Part6 as n6, count(*) as cnt6 FROM Lotto GROUP BY Part6 ORDER BY cnt6 DESC LIMIT 1)"))
                {
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
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLiteException: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception: " + ex.Message);
            }

        }

        //당첨번호 1 ~ 6 번중 전체 가장 많은 개수 체크
        private void button8_Click(object sender, EventArgs e)
        {
            try
            {
                List<Tuple<int, int>> resultList = new List<Tuple<int, int>>();

                for (int i = 1; i <= 6; i++)
                {
                    // TOP 10
                    SQLiteDataReader r = reader($"SELECT Part{i}, count(*) as cnt FROM Lotto GROUP BY Part{i} ORDER BY cnt DESC LIMIT 10");

                    while (r.Read())
                    {
                        int number = Convert.ToInt32(r[$"Part{i}"]);
                        int count = Convert.ToInt32(r["cnt"]);
                        resultList.Add(Tuple.Create(number, count));
                    }
                }

                resultList.Sort((x, y) => y.Item2.CompareTo(x.Item2));

                foreach (var tuple in resultList)
                {
                    Console.WriteLine("번호:" + tuple.Item1 + " 개수:" + tuple.Item2);
                }
            }
            catch (SQLiteException ex)
            {
                Console.WriteLine("SQLiteException: " + ex.Message);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception: " + ex.Message);
            }
        }

    }


    public class LotteItem
    {
        public int rIndex { get; set; }
        public string Date { get; set; }
        public int Part1 { get; set; }
        public int Part2 { get; set; }
        public int Part3 { get; set; }
        public int Part4 { get; set; }
        public int Part5 { get; set; }
        public int Part6 { get; set; }
        public int Bonus { get; set; }
    }


}
