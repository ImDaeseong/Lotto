namespace WindowsFormsApplication1
{
    partial class Form1
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            this.lstView = new System.Windows.Forms.ListView();
            this.label1 = new System.Windows.Forms.Label();
            this.txtrNum = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.txtPart1 = new System.Windows.Forms.TextBox();
            this.txtPart2 = new System.Windows.Forms.TextBox();
            this.txtPart3 = new System.Windows.Forms.TextBox();
            this.txtPart4 = new System.Windows.Forms.TextBox();
            this.txtPart5 = new System.Windows.Forms.TextBox();
            this.txtPart6 = new System.Windows.Forms.TextBox();
            this.btnAdd = new System.Windows.Forms.Button();
            this.button1 = new System.Windows.Forms.Button();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.button4 = new System.Windows.Forms.Button();
            this.button5 = new System.Windows.Forms.Button();
            this.button6 = new System.Windows.Forms.Button();
            this.button7 = new System.Windows.Forms.Button();
            this.button8 = new System.Windows.Forms.Button();
            this.label3 = new System.Windows.Forms.Label();
            this.txtDate = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.txtBonus = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // lstView
            // 
            this.lstView.Location = new System.Drawing.Point(13, 13);
            this.lstView.Name = "lstView";
            this.lstView.Size = new System.Drawing.Size(707, 392);
            this.lstView.TabIndex = 0;
            this.lstView.UseCompatibleStateImageBehavior = false;
            this.lstView.ColumnClick += new System.Windows.Forms.ColumnClickEventHandler(this.lstView_ColumnClick);
            this.lstView.DrawColumnHeader += new System.Windows.Forms.DrawListViewColumnHeaderEventHandler(this.lstView_DrawColumnHeader);
            this.lstView.DrawItem += new System.Windows.Forms.DrawListViewItemEventHandler(this.lstView_DrawItem);
            this.lstView.DrawSubItem += new System.Windows.Forms.DrawListViewSubItemEventHandler(this.lstView_DrawSubItem);
            this.lstView.SelectedIndexChanged += new System.EventHandler(this.lstView_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(15, 423);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 12);
            this.label1.TabIndex = 1;
            this.label1.Text = "회차";
            // 
            // txtrNum
            // 
            this.txtrNum.Location = new System.Drawing.Point(50, 420);
            this.txtrNum.Name = "txtrNum";
            this.txtrNum.Size = new System.Drawing.Size(100, 21);
            this.txtrNum.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(11, 455);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 12);
            this.label2.TabIndex = 3;
            this.label2.Text = "당첨번호";
            // 
            // txtPart1
            // 
            this.txtPart1.Location = new System.Drawing.Point(68, 451);
            this.txtPart1.Name = "txtPart1";
            this.txtPart1.Size = new System.Drawing.Size(82, 21);
            this.txtPart1.TabIndex = 4;
            // 
            // txtPart2
            // 
            this.txtPart2.Location = new System.Drawing.Point(156, 452);
            this.txtPart2.Name = "txtPart2";
            this.txtPart2.Size = new System.Drawing.Size(82, 21);
            this.txtPart2.TabIndex = 5;
            // 
            // txtPart3
            // 
            this.txtPart3.Location = new System.Drawing.Point(244, 452);
            this.txtPart3.Name = "txtPart3";
            this.txtPart3.Size = new System.Drawing.Size(82, 21);
            this.txtPart3.TabIndex = 6;
            // 
            // txtPart4
            // 
            this.txtPart4.Location = new System.Drawing.Point(332, 451);
            this.txtPart4.Name = "txtPart4";
            this.txtPart4.Size = new System.Drawing.Size(82, 21);
            this.txtPart4.TabIndex = 7;
            // 
            // txtPart5
            // 
            this.txtPart5.Location = new System.Drawing.Point(420, 451);
            this.txtPart5.Name = "txtPart5";
            this.txtPart5.Size = new System.Drawing.Size(82, 21);
            this.txtPart5.TabIndex = 8;
            // 
            // txtPart6
            // 
            this.txtPart6.Location = new System.Drawing.Point(508, 451);
            this.txtPart6.Name = "txtPart6";
            this.txtPart6.Size = new System.Drawing.Size(82, 21);
            this.txtPart6.TabIndex = 9;
            // 
            // btnAdd
            // 
            this.btnAdd.Location = new System.Drawing.Point(13, 494);
            this.btnAdd.Name = "btnAdd";
            this.btnAdd.Size = new System.Drawing.Size(75, 23);
            this.btnAdd.TabIndex = 11;
            this.btnAdd.Text = "추가";
            this.btnAdd.UseVisualStyleBackColor = true;
            this.btnAdd.Click += new System.EventHandler(this.btnAdd_Click);
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(235, 494);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 12;
            this.button1.Text = "당첨번호 1";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(316, 494);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(75, 23);
            this.button2.TabIndex = 13;
            this.button2.Text = "당첨번호 2";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(397, 494);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(75, 23);
            this.button3.TabIndex = 14;
            this.button3.Text = "당첨번호 3";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // button4
            // 
            this.button4.Location = new System.Drawing.Point(478, 494);
            this.button4.Name = "button4";
            this.button4.Size = new System.Drawing.Size(75, 23);
            this.button4.TabIndex = 15;
            this.button4.Text = "당첨번호 4";
            this.button4.UseVisualStyleBackColor = true;
            this.button4.Click += new System.EventHandler(this.button4_Click);
            // 
            // button5
            // 
            this.button5.Location = new System.Drawing.Point(559, 494);
            this.button5.Name = "button5";
            this.button5.Size = new System.Drawing.Size(75, 23);
            this.button5.TabIndex = 16;
            this.button5.Text = "당첨번호 5";
            this.button5.UseVisualStyleBackColor = true;
            this.button5.Click += new System.EventHandler(this.button5_Click);
            // 
            // button6
            // 
            this.button6.Location = new System.Drawing.Point(640, 494);
            this.button6.Name = "button6";
            this.button6.Size = new System.Drawing.Size(75, 23);
            this.button6.TabIndex = 17;
            this.button6.Text = "당첨번호 6";
            this.button6.UseVisualStyleBackColor = true;
            this.button6.Click += new System.EventHandler(this.button6_Click);
            // 
            // button7
            // 
            this.button7.Location = new System.Drawing.Point(235, 523);
            this.button7.Name = "button7";
            this.button7.Size = new System.Drawing.Size(100, 23);
            this.button7.TabIndex = 18;
            this.button7.Text = "당첨번호 Top 6개";
            this.button7.UseVisualStyleBackColor = true;
            this.button7.Click += new System.EventHandler(this.button7_Click);
            // 
            // button8
            // 
            this.button8.Location = new System.Drawing.Point(341, 523);
            this.button8.Name = "button8";
            this.button8.Size = new System.Drawing.Size(113, 23);
            this.button8.TabIndex = 19;
            this.button8.Text = "당첨번호 전체 60";
            this.button8.UseVisualStyleBackColor = true;
            this.button8.Click += new System.EventHandler(this.button8_Click);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(166, 423);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(41, 12);
            this.label3.TabIndex = 20;
            this.label3.Text = "추첨일";
            // 
            // txtDate
            // 
            this.txtDate.Location = new System.Drawing.Point(213, 420);
            this.txtDate.Name = "txtDate";
            this.txtDate.Size = new System.Drawing.Size(82, 21);
            this.txtDate.TabIndex = 21;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(314, 423);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(41, 12);
            this.label4.TabIndex = 22;
            this.label4.Text = "보너스";
            // 
            // txtBonus
            // 
            this.txtBonus.Location = new System.Drawing.Point(361, 420);
            this.txtBonus.Name = "txtBonus";
            this.txtBonus.Size = new System.Drawing.Size(82, 21);
            this.txtBonus.TabIndex = 23;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(734, 572);
            this.Controls.Add(this.txtBonus);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.txtDate);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.button8);
            this.Controls.Add(this.button7);
            this.Controls.Add(this.button6);
            this.Controls.Add(this.button5);
            this.Controls.Add(this.button4);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.btnAdd);
            this.Controls.Add(this.txtPart6);
            this.Controls.Add(this.txtPart5);
            this.Controls.Add(this.txtPart4);
            this.Controls.Add(this.txtPart3);
            this.Controls.Add(this.txtPart2);
            this.Controls.Add(this.txtPart1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.txtrNum);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.lstView);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView lstView;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox txtrNum;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox txtPart1;
        private System.Windows.Forms.TextBox txtPart2;
        private System.Windows.Forms.TextBox txtPart3;
        private System.Windows.Forms.TextBox txtPart4;
        private System.Windows.Forms.TextBox txtPart5;
        private System.Windows.Forms.TextBox txtPart6;
        private System.Windows.Forms.Button btnAdd;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
        private System.Windows.Forms.Button button4;
        private System.Windows.Forms.Button button5;
        private System.Windows.Forms.Button button6;
        private System.Windows.Forms.Button button7;
        private System.Windows.Forms.Button button8;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox txtDate;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtBonus;
    }
}

