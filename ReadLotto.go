// ReadLotto
package main

import (
	"fmt"
	"io"
	"os"
	"xlsx" //https://github.com/tealeg/xlsx
)

func WriteLottoString(sPath, sText string) {

	file, err := os.OpenFile(sPath, os.O_RDWR|os.O_APPEND, 0660)
	if os.IsNotExist(err) {
		file, err = os.Create(sPath)
	}
	defer file.Close()

	if err != nil {
		return
	}

	n, err := io.WriteString(file, sText)
	if err != nil {
		fmt.Println(n, err)
		return
	}
}

func getLotto() {

	sSavePath := fmt.Sprintf("D:\\test\\Lotto.txt")

	sFilePath := "D:\\test\\lo.xlsx"
	xlFile, err := xlsx.OpenFile(sFilePath)
	if err != nil {
		fmt.Printf("Read Error ", err)
	}

	for _, sheet := range xlFile.Sheets {
		for rIndex, row := range sheet.Rows {

			if rIndex == 0 {
				//제목 row
				continue
			}

			if rIndex == 1 {
				//제목 row
				continue
			}

			cell1, _ := row.Cells[0].String()
			cell2, _ := row.Cells[1].String()
			cell3, _ := row.Cells[2].String()
			cell4, _ := row.Cells[3].String()
			cell5, _ := row.Cells[4].String()
			cell6, _ := row.Cells[5].String()
			cell7, _ := row.Cells[6].String()
			cell8, _ := row.Cells[7].String()
			cell9, _ := row.Cells[8].String()

			sLine := fmt.Sprintf("%s|%s|%s|%s|%s|%s|%s|%s|%s", cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9)
			//fmt.Println(sLine)

			WriteLottoString(sSavePath, sLine+"\n")
		}
	}
}

func main() {

	getLotto()
}
