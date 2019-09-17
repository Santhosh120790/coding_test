package com.ge.exercise2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayQuadrantUtil {
	private static final Logger logger = LogManager.getLogger(ArrayQuadrantUtil.class);

	char[][] data;

	public ArrayQuadrantUtil(char[][] data) {
		this.data = data;
	}

	public char[] getQuadrantValues(int row, int column) {
		char[] quadrantValues  = new char[4];
		int count =0;
		if(row<data.length-1 && column<data.length-1){
			quadrantValues[count++] = data[row][column];
			quadrantValues[count++]=data[row][column+1];
			quadrantValues[count++]=data[row+1][column+1];
			quadrantValues[count]=data[row+1][column];
		}
		return quadrantValues;
	}

	public char[] getColumnValues(int column){
		char[] columnValues  = new char[data.length];
		for(int row=0;row<data.length;row++){
			columnValues[row] = data[row][column];
		}
		return columnValues;
	}

	public char[] getRowValues(int row){
		char[] rowValues  = {};
		return (row <data.length)?data[row]:rowValues;
	}

	public static void main(String[] args) {
		char[][] data = {
				{'a', 'b', 'c', 'd'},
				{'e', 'f', 'g', 'h'},
				{'i', 'j', 'k', 'l'},
				{'m', 'n', 'o', 'p'}

		};

		ArrayQuadrantUtil util = new ArrayQuadrantUtil(data);
		logger.info("Length of an Array {},{}",data.length,data[1].length);
		logger.info("Get row values {}",util.getRowValues(3));
		logger.info("Get column values {}",util.getColumnValues(3));
		logger.info("Get column values {}",util.getQuadrantValues(2,2));

	}
}
