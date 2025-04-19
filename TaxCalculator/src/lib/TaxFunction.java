package lib;

public class TaxFunction {


	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
 
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isSingle, int numberOfChildren) {
		
		int tax = 0;

		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}

		// Batasi jumlah anak maksimum 3
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		// Hitung penghasilan kotor tahunan
		int grossIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
		
		// Hitung penghasilan tidak kena pajak (PTKP)
		int nonTaxableIncome = 54000000;

		if (!isSingle) { // Sudah menikah
			nonTaxableIncome += 4500000;
		}

		nonTaxableIncome += numberOfChildren * 4500000;

		// Hitung penghasilan bersih
		int netIncome = grossIncome - deductible;

		// Hitung pajak: 5% dari (penghasilan bersih - penghasilan tidak kena pajak)
		tax = (int) Math.round(0.05 * (netIncome - nonTaxableIncome));

		// Pajak tidak boleh negatif
		if (tax < 0) {
			return 0;
		} else {
			return tax;
		}
	}
}
