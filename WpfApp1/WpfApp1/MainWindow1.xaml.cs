using System;
using System.Collections.ObjectModel;
using System.Windows;

namespace WpfApp1
{
    public partial class MainWindow1 : Window
    {
        public ObservableCollection<Item1> ob_item1 { get; set; }

        public MainWindow1()
        {
            InitializeComponent();

            ob_item1 = new ObservableCollection<Item1>
            {
                new Item1 { rIndex = 1, Date = "2023-11-01", Bonus = "400000" },
                new Item1 { rIndex = 2, Date = "2023-11-11", Bonus = "1400000" },
                new Item1 { rIndex = 3, Date = "2023-11-21", Bonus = "6400000" }
            };

            lstView.ItemsSource = ob_item1;
            lstView.SelectionChanged += LstView_SelectionChanged;
        }

        private void LstView_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            if (lstView.SelectedItem != null)
            {
                Item1 selectedItem = (Item1)lstView.SelectedItem;
                int rIndex = selectedItem.rIndex;
                string sDate = selectedItem.Date; 
                string sBonus = selectedItem.Bonus;

                Console.WriteLine("rIndex:" + rIndex);
                Console.WriteLine("sDate:" + sDate);
                Console.WriteLine("sBonus:" + sBonus);
            }
        }
    }

    public class Item1
    {
        public int rIndex { get; set; }
        public string Date { get; set; }
        public string Bonus { get; set; }
    }
}
