package javasrc;

import java.util.List;
import java.util.Objects;

public class GameHelper
{
    public List<Integer> moveAndMergeEqual(List<Integer> list)
    {
        if (!list.isEmpty())
        {
            nullToEnd(list);
            for (int i = 0; i < list.size() - 1; i++)
            {
                var currentI = list.get(i);
                var nextI = list.get(i + 1);
                if ((Objects.equals(currentI, nextI)) && (currentI != null))
                {
                    list.set(i, nextI * 2);
                    list.set(i + 1, null);
                }
            }
            nullToEnd(list);
        }
        return list;
    }

    private void nullToEnd(List<Integer> list)
    {
        for(int i=0; i<list.size(); i++)
        {
            for(int j=i+1; j<list.size(); j++)
            {
                if(list.get(i)==null)
                {
                    list.set(i, list.get(j));
                    list.set(j, null);
                }
            }
        }
    }

}
