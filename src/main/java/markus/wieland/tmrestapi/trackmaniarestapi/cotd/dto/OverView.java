package markus.wieland.tmrestapi.trackmaniarestapi.cotd.dto;

import java.util.ArrayList;
import java.util.List;

public class OverView {

    private List<MonthOverView> overView;

    public OverView() {
        this.overView = new ArrayList<>();
    }

    public void add(int year, int month) {
        for (MonthOverView monthOverView : overView) {
            if (monthOverView.getYear() == year && monthOverView.getMonth() == month) return;
        }
        overView.add(new MonthOverView(year, month));
    }

    public List<MonthOverView> getOverView() {
        return overView;
    }

    public void setOverView(List<MonthOverView> overView) {
        this.overView = overView;
    }
}
