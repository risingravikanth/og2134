package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface DataService {
		public String getCrudeOilData(Map<String,List> selectedOptions);
        public String getExplorationData(Map<String,List> selectedOptions);
        public String getLngData(Map<String,List> selectedOptions);
        public String getNaturalGasData(Map<String,List> selectedOptions);
        public String getPipeLineData(Map<String,List> selectedOptions);
        public String getRefineryData(Map<String,List> selectedOptions);
        public String getStorageData(Map<String,List> selectedOptions);
}
