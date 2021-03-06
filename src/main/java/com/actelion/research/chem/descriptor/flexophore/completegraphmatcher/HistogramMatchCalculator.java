package com.actelion.research.chem.descriptor.flexophore.completegraphmatcher;

import com.actelion.research.chem.descriptor.flexophore.DistHist;
import com.actelion.research.chem.descriptor.flexophore.generator.ConstantsFlexophoreGenerator;

/**
 * 
 * 
 * HistogramMatchCalculator
 * <p>Copyright: Actelion Ltd., Inc. All Rights Reserved
 * This software is the proprietary information of Actelion Pharmaceuticals, Ltd.
 * Use is subject to license terms.</p>
 * @author Modest von Korff
 * @version 1.0
 * Oct 2, 2012 MvK: Start implementation
 * May 15 2013 MvK: Heavy bug detected. Wrong similarity results. reset() added.
 * Mar 01 2016 MvK sliding filter added.
 * 2020 March, re-written
 */
public class HistogramMatchCalculator {

	public static double getSimilarity(DistHist dh1, int indexDistHist1At1, int indexDistHist1At2, DistHist dh2, int indexDistHist2At1, int indexDistHist2At2){

		int indexPostStartDistHist1 = dh1.getIndexPosStartForDistHist(indexDistHist1At1, indexDistHist1At2);
		int indexPostStartDistHist2 = dh2.getIndexPosStartForDistHist(indexDistHist2At1, indexDistHist2At2);

		int n = ConstantsFlexophoreGenerator.BINS_HISTOGRAM;

		double sumMin = 0;
		double sumMax = 0;

		for (int i = 0; i < n; i++) {
			int v1 = dh1.getValueAtAbsolutePosition(indexPostStartDistHist1+i);
			int v2 = dh2.getValueAtAbsolutePosition(indexPostStartDistHist2+i);
			sumMin += Math.min(v1, v2);
			sumMax += Math.max(v1, v2);
		}

		double score = sumMin / sumMax;

		return score;

	}

	public static double getPercentageOverlap(DistHist dh1, int indexDistHist1At1, int indexDistHist1At2, DistHist dh2, int indexDistHist2At1, int indexDistHist2At2){

		int indexPostStartDistHist1 = dh1.getIndexPosStartForDistHist(indexDistHist1At1, indexDistHist1At2);
		int indexPostStartDistHist2 = dh2.getIndexPosStartForDistHist(indexDistHist2At1, indexDistHist2At2);

		int n = ConstantsFlexophoreGenerator.BINS_HISTOGRAM;

		double sumMin = 0;
		double sum1 = 0;
		double sum2 = 0;

		for (int i = 0; i < n; i++) {
			int v1 = dh1.getValueAtAbsolutePosition(indexPostStartDistHist1+i);
			int v2 = dh2.getValueAtAbsolutePosition(indexPostStartDistHist2+i);
			sumMin += Math.min(v1, v2);

			sum1 += v1;
			sum2 += v2;
		}

		double score = sumMin / Math.max(sum1, sum2);;

		return score;

	}



}
