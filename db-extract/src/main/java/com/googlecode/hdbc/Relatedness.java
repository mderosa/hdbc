package com.googlecode.hdbc;

public class Relatedness {

	/**
	 * Calculates the distance between two strings using the Wagner-Fisher
	 * algorithm for Levenshtein distance.
	 */
	public final int columnRelatedness(final String columnName1, final String columnName2) {
		int[] [] matrix = new int[columnName1.length() + 1] [columnName2.length() + 1];

		for (int i = 0; i <= columnName1.length(); i++) {
			matrix[i][0] = i;
		}
		for (int j = 0; j <= columnName2.length(); j++) {
			matrix[0][j] = j;
		}

		int cost;
		for (int i = 1; i <= columnName1.length(); i++) {
			for (int j = 1; j <= columnName2.length(); j++) {
				if (columnName1.charAt(i - 1) == columnName2.charAt(j - 1)) {
					cost = 0;
				} else {
					cost = 1;
				}
				matrix[i][j] = min(matrix[i - 1][j] + 1,
							       matrix[i][j - 1] + 1,
							       matrix[i - 1][j - 1] + cost);
			}
		}
		return matrix[columnName1.length()] [columnName2.length()];
	}

	private int min(final int a, final int b, final int c) {
		int temp = Math.min(a, b);
		temp = Math.min(temp, c);
		return temp;
	}
}
