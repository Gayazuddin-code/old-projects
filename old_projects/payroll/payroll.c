#include <stdio.h>
#include <conio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <windows.h>
#include <time.h>
int tpri = 0;
int vpri = 0;
int hod = 0;
int chod = 0, ehod = 0, eehod = 0, mhod = 0, ahod = 0, mbhod = 0;
struct emp
{
	int id;
	char name[40], dep[20], desig[20];
	int basic, hra, da, others;
	int pf, insurance;
	float gross, net;
	int worked, leaves, remaining_leaves, loss_pay;
};

char str[50];
int max_leavs = 25;
char fname[] = {"path"};

void append();
void display();
void displayAll();
void modify();
void search();
void del();
void slip(struct emp, int, int);
int count();

void wel_come()
{
	system("COLOR 87");
	getch();
	printf("\n\n\n\n\n\n\n\n");
	printf("\t\t\t\t    ======================================\t\t\t\t\t\t\n");
	printf("\t\t\t\t\t            WELCOME TO \t\t\t\t\t\t\t\t\n");
	printf("\t\t\t\t           PAYROLL MANAGEMENMT SYSTEM\t \t\t\t\t\t\t\n");
	printf("\t\t\t\t    ======================================\t\t\t\t\t\t\n");

	printf("\n\n\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     \t\t\t\t\t\t\t\tDeveloped by:\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     \t\t\t\t\t\t\t\tname 1\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     \t\t\t\t\t\t\t\tname 2\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     \t\t\t\t\t\t\t\tname 3\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     \t\t\t\t\t\t\t\tname 4\n");
	printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t     \t\t\t\t\t\t\t\tname 5\n\n\n\a\a");

	printf("Press any key to continue %c.........\t\t\t\t\t\t\t\t\t\t\n", 1);

	getch();
	system("cls");
}

int login()
{
	system("COLOR 0A");
	int a = 0, i = 0;
	char uname[10], c = ' ';
	char pword[10], code[10];
	char user[10] = "user";
	char pass[10] = "pass";

	printf("\n\n\n\n\n\n\n\n\n\n\n\t\t\t\t\tLOGIN\n");
	printf(" \n\t\t\t\t\tUSERNAME : ");
	scanf("%s", &uname);
	printf(" \n\t\t\t\t\tPASSWORD : ");
	while (i < 10)
	{
		pword[i] = getch();
		c = pword[i];
		if (c == 13)
			break;
		else
			printf(" ");
		i++;
	}
	pword[i] = '\0';
	i = 0;
	if (strcmp(uname, "user") == 0 && strcmp(pword, "pass") == 0)
	{
		printf("\n\n\n\t\t\t\t\tLOGIN IS SUCCESSFUL\a\a\a");
		printf("\n\n\n\t\t\t\t\tPress any key to continue...");
		getch();
		system("cls");
		return 1;
	}
	else
	{
		printf("\n\n\n\t\t\t\t\tLOGIN IS UNSUCESSFUL\a\a\a\a\a");
		a++;

		getch();
	}
	system("cls");
	printf("\a\a");
	return 0;
}

//valid name

char *myName(char ch[])
{
	int i, f;
	for (i = 0; i < strlen(ch); i++)
	{
		if (isalpha(ch[i]))
		{
			f = 0;
		}
		else if (ch[i] == ' ')
		{
			f = 0;
		}
		else
		{
			f = 1;
			break;
		}
	}
	if (f == 0)
	{
		return ch;
	}
	else
	{
		return "not valid";
	}
}

int prisearch()
{
	FILE *fp;
	struct emp t;
	int found = 0;
	char tpri[] = "principal";
	fp = fopen(fname, "rb");

	while (1)
	{
		fread(&t, sizeof(t), 1, fp);

		if (strcmp(tpri, t.desig) != 0)
			;
		{
			found = 1;
		}
		if (feof(fp)) //fefo -> end of file.
		{

			break;
		}
	}
	if (found == 1)
	{
		printf("\nprincpal already exist");
		return 1;
	}
	fclose(fp);
}

char *des()
{
	int m;
	float a, c;
	printf("\nEnter the type of Designation\n1.Teaching\t\t\t2.Non-teaching\nEnter your choice: ");
	scanf("%f", &c);
	if (c == 1)
	{
	pri:
		printf("\n1.Principal    	   	 3.HOD 	         5.Associate Professor\n");
		printf("2.Vice-Principal         4.Professor     6.Assistant Professor");
		while (1)
		{
			printf("\nEnter Designation no: ");
			scanf("%f", &a);

			if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6)
			{
				m = (int)a;
				switch (m)
				{

				case 1:
					if (tpri == 1)
					{
						printf("\nprincpal already exist");
						goto pri;
					}
					else if (tpri == 0)
					{
						tpri = 1;
						return "Principal";
					}
					break;
				case 2:
					if (vpri == 1)
					{
						printf("\nVice-princpal already exist");
						goto pri;
					}
					else if (vpri == 0)
					{
						vpri = 1;
						return "Vice-Principal";
					}
					break;
				case 3:
					if (hod == 1 && chod == 0)
					{
						chod = 1;
						return "HOD";
					}
					else if (chod == 1)
					{
						printf("\nhod already exist");
						goto pri;
					}
					if (hod == 2 && ehod == 0)
					{
						ehod = 1;
						return "HOD";
					}
					else if (ehod == 1)
					{
						printf("\nhod already exist");
						goto pri;
					}
					if (hod == 3 && eehod == 0)
					{
						eehod = 1;
						return "HOD";
					}
					else if (eehod == 1)
					{
						printf("\nhod already exist");
						goto pri;
					}
					if (hod == 4 && mhod == 0)
					{
						mhod = 1;
						return "HOD";
					}
					else if (mhod == 1)
					{
						printf("\nhod already exist");
						goto pri;
					}
					if (hod == 5 && ahod == 0)
					{
						ahod = 1;
						return "HOD";
					}
					else if (ahod == 1)
					{
						printf("\nhod already exist");
						goto pri;
					}
					if (hod == 6 && mbhod == 0)
					{
						mbhod = 1;
						return "HOD";
					}
					else if (mbhod == 1)
					{
						printf("\nhod already exist");
						goto pri;
					}
					break;
				case 4:
					return "Professor";
					break;
				case 5:
					return "Associate Professor";
					break;
				case 6:
					return "Assistant Professor";
					break;
				default:
					printf("Invalid designation");
					break;
				}
				break;
			}
			else
			{
				printf("Invalid designation");
			}
		}
	}
	else if (c == 2)

	{

		printf("\n1.Programmer\t   		 2.Supervisor\t    		   3.Instructor\n4.Lab Assistant\t		 5.Attender");
		while (1)
		{
			printf("\nEnter Designation no: ");
			scanf("%f", &a);

			if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5)
			{
				m = (int)a;
				switch (m)
				{

				case 1:
					return "Programmer";
					break;
				case 2:
					return "Supervisor";
					break;
				case 3:
					return "Instructor";
					break;
				case 4:
					return "Lab Assistant";
					break;
				case 5:
					return "Attender";
					break;
				default:
					printf("Invalid designation");
					break;
				}
				break;
			}
			else
			{
				printf("Invalid designation");
			}
		}
	}

	else
	{
		printf("Invalid designation type");
		des();
	}
}

char *dept()
{
	int m;
	float a;

	printf("\n1.CSE\t                      3.ECE\t                5.AI");
	printf("\n2.EEE\t                      4.MECH\t               6.MBA");
	while (1)
	{
		printf("\nEnter Departments no: ");
		scanf("%f", &a);
		hod = a;
		if (a == 1 || a == 2 || a == 3 || a == 4 || a == 5 || a == 6)
		{
			m = (int)a;
			switch (m)
			{
			case 1:
				return "CSE";
				break;
			case 2:
				return "EEE";
				break;
			case 3:
				return "ECE";
				break;
			case 4:
				return "MECH";
				break;
			case 5:
				return "AI";
				break;
			case 6:
				return "MBA";
			}
			break;
		}
		else
		{
			printf("Invalid designation");
			continue;
		}
	}
}

int main()
{

	int f, j = 0, i, ch, authorized = 0;
	FILE *fp;
	fp = fopen(fname, "ab");
	fclose(fp);
	wel_come();
	authorized = login();

	while (1)
	{
		if (authorized == 0)
		{
			authorized = login();
		}
		else
		{
			break;
		}
	}
	while (authorized)
	{
		system("COLOR F1");

		printf("\n=================Employee Payroll System==================\n\n");

		printf("1. Add New Record\t\t2. Show All Records\n");
		printf("3. Print Salary Slip\t\t4. Update\n");
		printf("5. Search\t\t\t6. Delete\n");
		printf("7. Exit\n");
		printf("\n==========================================================\n\n");
		printf("\n\a\a\a\aEnter your Choice:");
		scanf("%d", &ch);

		switch (ch)
		{

		case 2:
			displayAll();
			break;

		case 1:
			append();
			break;

		case 3:
			display(); // -> printing the sipl.
			break;

		case 4:
			modify();
			break;

		case 5:
			search();
			break;

		case 6:
			del();
			break;

		case 7:
			printf("\a\a\a\a");
			getch();
			system("cls");
			system("COLOR 4C");
			getch();
			exit(1);
			break;
		default:
			printf("\nEnter a vaild option!\n");
		}
	}
	getch();
	system("cls");
	return 0;
}

//Append function
void append()
{
	system("COLOR 8F");
	char ch;
	int i = 1;
	FILE *fp;
	struct emp t1;
	system("cls");
	t1.loss_pay = 0;
	fp = fopen(fname, "ab");
	i = count();
	i = vaid_id(i);
	t1.id = i;
vnam:
	printf("Enter name:");
	getch();
	gets(t1.name);
	strcpy(t1.name, myName(t1.name));
	if (strcmp(t1.name, "not valid") == 0)
	{
		goto vnam;
	}
	strcpy(t1.dep, dept());
	printf("\n");
	strcpy(t1.desig, des());
	printf("\n");
	printf("\nEnter Basic salary:");
	scanf("%d", &t1.basic);
	if (t1.basic < 10000)
	{
		t1.basic = 10000;
	}
	else if (t1.basic > 200000)
	{
		t1.basic = 200000;
	}
	getchar();
	printf("House Rent Allowance(%c):", '%');
	scanf("%d", &t1.hra);
	printf("Dearness Allowance(%c):", '%');
	scanf("%d", &t1.da);
	printf("Other Allowance(%c):", '%');
	scanf("%d", &t1.others);
	printf("Enter the deductions:\n");
	printf("Provident Fund:");
	scanf("%d", &t1.pf);
	printf("Insurance(LIC):");
	scanf("%d", &t1.insurance);
	while (1)
	{
		printf("Working days:");
		scanf("%d", &t1.worked);
		if (t1.worked < 31 && t1.worked > 0)
		{
			break;
		}
		else
		{
			printf("\nEnter valid number of days\n");
		}
	}
	while (1)
	{
		printf("Leave days:");
		scanf("%d", &t1.leaves);
		if (t1.leaves < 0)
		{
			t1.leaves = 0;
		}
		else if (t1.leaves > 31)
		{
			t1.leaves = 31;
		}
		else
		{
			break;
		}
	}
	t1.remaining_leaves = max_leavs - t1.leaves;
	if (t1.remaining_leaves <= 0)
	{
		t1.loss_pay = 0 - t1.remaining_leaves;
		t1.remaining_leaves = 0;
	}
	fwrite(&t1, sizeof(t1), 1, fp);

	fclose(fp);
	printf("\a\a");
	getch();
	system("cls");
}

int count()
{
	FILE *fp;
	struct emp t;
	int i = 1;
	fp = fopen(fname, "rb");
	while (1)
	{
		fread(&t, sizeof(t), 1, fp);

		if (feof(fp))
		{
			//return 0;
			//	i++;
			//	t.id = t.id;
			break;
		}
		i++;
		//t.id = t.id;
	}
	fclose(fp);
	//printf("count %d",i);
	return i;
}

int vaid_id(int i)
{
	FILE *fp;
	struct emp t;
	fp = fopen(fname, "rb");
	while (1)
	{
		fread(&t, sizeof(t), 1, fp);

		if (feof(fp))
		{
			//return 0;
			//i++;
			//	t.id = t.id;
			break;
		}
		if (i == t.id)
		{
			i += 1;
		}
		//t.id = t.id;
	}
	fclose(fp);
	//printf("count %d",i);
	return i;
}

// modify function
void modify()
{
	system("COLOR F8");
	FILE *fp, *fp1;
	struct emp t;
	int id, found = 0, count = 0;
	t.loss_pay = 0;
	system("cls");
	fp = fopen(fname, "rb");
	fp1 = fopen("temp.dat", "wb");
	printf("\nEnter Emp ID you want to Modify:");
	scanf("%d", &id);

	while (1)
	{

		fread(&t, sizeof(t), 1, fp);

		if (feof(fp))
		{
			break;
		}
		if (t.id == id)
		{
			found = 1;
			fflush(stdin);
			while (1)
			{
				puts("\nEnter name:");
				gets(t.name);
				strcpy(t.name, myName(t.name));
				if (strcmp(t.name, "not valid") == 0)
				{
					continue;
				}
				else
				{
					break;
				}
			}
			strcpy(t.desig, des());
			printf("\n\n");
			strcpy(t.dep, dept());
			printf("\nEnter Basic salary:");
			scanf("%d", &t.basic);
			if (t.basic < 10000)
			{
				t.basic = 10000;
			}
			else if (t.basic > 200000)
			{
				t.basic = 200000;
			}
			getchar();
			printf("House Rent Allowance(%c):", '%');
			scanf("%d", &t.hra);
			printf("Dearness Allowance(%c):", '%');
			scanf("%d", &t.da);
			printf("Other Allowance(%c):", '%');
			scanf("%d", &t.others);
			printf("Enter the deductions:\n");
			printf("Provident Fund:");
			scanf("%d", &t.pf);
			printf("Insurance:");
			scanf("%d", &t.insurance);
			while (1)
			{
				printf("Working days:");
				scanf("%d", &t.worked);
				if (t.worked < 31 && t.worked > 0)
				{
					break;
				}
				else
				{
					printf("\nEnter valid number of days\n");
				}
			}
			while (1)
			{
				printf("Leave days:");
				scanf("%d", &t.leaves);
				if (t.leaves < 0)
				{
					t.leaves = 0;
				}
				else if (t.leaves > 31)
				{
					t.leaves = 31;
				}
				else
				{
					break;
				}
			}
			t.remaining_leaves = max_leavs - t.leaves;
			if (t.remaining_leaves <= 0)
			{
				t.loss_pay = 0 - t.remaining_leaves;
				t.remaining_leaves = 0;
			}
			fwrite(&t, sizeof(t), 1, fp1);
		}
		else
		{
			fwrite(&t, sizeof(t), 1, fp1);
		}
	}

	fclose(fp);
	fclose(fp1);

	if (found == 0)
	{
		printf("No Record Found\n\n");
	}
	else
	{
		fp = fopen(fname, "wb");
		fp1 = fopen("temp.dat", "rb");

		while (1)
		{
			fread(&t, sizeof(t), 1, fp1);

			if (feof(fp1))
			{
				break;
			}
			fwrite(&t, sizeof(t), 1, fp);
		}
	}
	fclose(fp);
	fclose(fp1);
	printf("\a\a");
	getch();
	system("cls");
}

// Display function -> used to print slip
void display()
{
	system("COLOR F0");
	FILE *fp;
	struct emp t;
	int id, found = 0;
	time_t ti;
	time(&ti);
	system("cls");
	fp = fopen(fname, "rb");

	printf("\nEnter Emp ID:");
	scanf("%d", &id);

	while (1)
	{
		fread(&t, sizeof(t), 1, fp);

		if (feof(fp))
		{
			break;
		}
		if (t.id == id)
		{
			found = 1;

			int gross, net;

			gross = t.basic + (t.hra * t.basic) / 100 + (t.da * t.basic) / 100 + (t.others * t.basic) / 100;
			net = gross - (t.pf + t.insurance + (t.basic / 30 * t.loss_pay));

			printf("\n=================================================================================\n");
			printf("\t\t\tBITM Ballari\n");
			printf("\t\t Employee Details of %s\n", t.name);
			printf("=================================================================================\n");
			printf("Emp ID : %d\tName : %s\tPay slip for : %s\n", t.id, t.name, ctime(&ti));
			printf("Department : %s\tDesignation : %s\n\n", t.dep, t.desig);
			printf("=================================================================================\n");
			printf("Basic : %d\t\tPF : %d\n\n", t.basic, t.pf);
			printf("DA : %d\t\t\tInsurance : %d\n\n", t.da, t.insurance);
			printf("HRA : %d\t\t\tOthers : %d\n\n", t.hra, t.others);
			printf("=================================================================================\n");
			printf("Gross Salary = %d\t\n\n", gross);
			printf("Net Salary = %d\t\n\n", net);
			slip(t, gross, net);
		}
	}
	if (found == 0)
	{
		printf("\nNo Record Found");
	}
	printf("\a\a");
	fclose(fp);
	getch();
	system("cls");
}

void search()
{
	system("COLOR 04");
	FILE *fp;
	struct emp t;
	int found = 0;
	int id;
	system("cls");
	fp = fopen(fname, "rb");
	printf("\nEnter Employee ID:");
	scanf("%d", &id);

	while (1)
	{
		fread(&t, sizeof(t), 1, fp);

		if (feof(fp)) //fefo -> end of file.
		{
			break;
		}
		if (id == t.id)
		{
			printf("\t\t Employee Details of %s\n\n", t.name);
			printf("===============================================================================\n\n");

			printf("Name\tBasic\tHRA\tDA\tOTHERS\tPF\tInsurance\n\n");

			printf("%s\t", t.name);
			printf("%d\t", t.basic);
			printf("%d\t", t.hra);
			printf("%d\t", t.da);
			printf("%d\t", t.others);
			printf("%d\t", t.pf);
			printf("%d\t\n\n", t.insurance);

			found = 1;
		}
	}
	if (found == 0)
	{
		printf("\nNo Record Found");
	}
	fclose(fp);
	printf("\a\a");
	getch();
	system("cls");
}

//display all employees
void displayAll()
{
	system("COLOR B0");
	FILE *fp;
	struct emp t;
	int f;
	fp = fopen(fname, "rb");
	system("cls");
	//f = count();
	//printf("number of records are : %d",f);
	//if(f <= 0)
	//{
	//	printf("\nNo date found.");
	//}
	////else
	////{
	printf("\n===============================================================\n\n");
	printf("\t\t All Employee Details\n\n");
	printf("=================================================================\n\n");

	printf("ID\tName\tBasic\tHRA\tDA\tOthers\tPF\tInsurance\n\n");

	while (1)
	{
		fread(&t, sizeof(t), 1, fp);

		if (feof(fp))
		{
			break;
		}
		printf("%d\t", t.id);
		printf("%s\t", t.name);
		printf("%d\t", t.basic);
		printf("%d\t", t.hra);
		printf("%d\t", t.da);
		printf("%d\t", t.others);
		printf("%d\t", t.pf);
		printf("%d\t\n\n", t.insurance);
	}

	printf("=================================================================\n\n");
	//}
	fclose(fp);
	printf("\a\a");
	getch();
	system("cls");
}

//delete a record
void del()
{
	struct emp t;
	int temp_id;
	int found = 0;
	FILE *fpd, *fp;
	system("cls");
	printf("\nEnter the id to be deleted : ");
	scanf("%d", &temp_id);
	fp = fopen(fname, "rb");
	fpd = fopen("tmp.txt", "wb");
	while (1)
	{

		fread(&t, sizeof(t), 1, fp);

		if (feof(fp))
		{
			break;
		}
		if (temp_id != t.id)
		{
			fwrite(&t, sizeof(t), 1, fpd);
		}
		found = 1;
	}
	if (found == 1)
	{
		printf("\nRecord deleted\n");
	}
	else
	{
		printf("\nNo record found\n");
	}
	fclose(fp);
	fclose(fpd);
	printf("\a\a\a\a\a");
	getch();
	system("cls");
	remove(fname);
	rename("tmp.txt", fname);
}

void slip(struct emp t, int gross, int net)
{

	FILE *fps;
	time_t ti;
	time(&ti);
	fps = fopen("path, "w");
	fputs("\n=================================================================================\n",fps);
    fprintf(fps,"\t\t\tBITM Ballari\n");
    fprintf(fps,"\t\t Employee Details of %s\n", t.name);
    fprintf(fps,"===================================================  ==============================\n");
    fprintf(fps,"Emp ID : %d\tName : %s\tPay slip for : %s\n",t.id,t.name,ctime(&ti));
	fprintf(fps,"Department : %s\tDesignation : %s\n\n",t.dep,t.desig);
	fprintf(fps,"=================================================================================\n");
	fprintf(fps,"Basic : %d\t\tPF : %d\n\n",t.basic,t.pf);
	fprintf(fps,"DA : %d\t\t\tInsurance : %d\n\n",t.da,t.insurance);
	fprintf(fps,"HRA : %d\t\t\tOthers : %d\n\n",t.hra,t.others);
	fprintf(fps,"=================================================================================\n");
	fprintf(fps,"Gross Salary = %d\t\n\n", gross);
    fprintf(fps,"Net Salary = %d\t\n\n", net);
    fclose(fps);
}

