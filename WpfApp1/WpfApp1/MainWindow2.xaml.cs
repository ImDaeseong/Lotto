using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Windows;
using System.Windows.Controls;

namespace WpfApp1
{   
    public partial class MainWindow2 : Window
    {
        public ObservableCollection<Item2> ob_item2 { get; set; }

        public MainWindow2()
        {
            InitializeComponent();

            ob_item2 = new ObservableCollection<Item2>
            {
                new Item2 { rIndex = 1, Date = "2023-11-01", Bonus = "400000" },
                new Item2 { rIndex = 2, Date = "2023-11-11", Bonus = "1400000" },
                new Item2 { rIndex = 3, Date = "2023-11-21", Bonus = "6400000" }
            };

            lstView.ItemsSource = ob_item2;
            lstView.SelectionChanged += LstView_SelectionChanged;
        }

        //
        private void LstView_SelectionChanged(object sender, System.Windows.Controls.SelectionChangedEventArgs e)
        {
            if (lstView.SelectedItem != null)
            {
                Item2 selectedItem = (Item2)lstView.SelectedItem;
                int rIndex = selectedItem.rIndex;
                string sDate = selectedItem.Date;
                string sBonus = selectedItem.Bonus;

                Console.WriteLine("rIndex:" + rIndex);
                Console.WriteLine("sDate:" + sDate);
                Console.WriteLine("sBonus:" + sBonus);
            }
        }

        //전체 체크박스 클릭
        private void CheckBox_All_Click(object sender, RoutedEventArgs e)
        {
            CheckBox checkBox = (CheckBox)sender;

            foreach (Item2 item in ob_item2)
            {
                item.IsSelected = checkBox.IsChecked ?? false;
                Console.WriteLine("item.IsSelected:" + item.IsSelected);
            }
        }

        //개별 체크박스 클릭
        private void CheckBox_Row_Click(object sender, RoutedEventArgs e)
        {
            CheckBox checkBox = (CheckBox)sender;
            if (checkBox.DataContext is Item2 clickedItem)
            {
                Console.WriteLine("clickedItem.rIndex:" + clickedItem.rIndex);
                Console.WriteLine("clickedItem.IsSelected:" + clickedItem.IsSelected);
            }
        }
        
    }

    public class Item2 : INotifyPropertyChanged
    {
        private bool _isSelected;

        public int rIndex { get; set; }
        public string Date { get; set; }
        public string Bonus { get; set; }

        public bool IsSelected
        {
            get { return _isSelected; }
            set
            {
                if (_isSelected != value)
                {
                    _isSelected = value;
                    OnPropertyChanged(nameof(IsSelected));
                }
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
