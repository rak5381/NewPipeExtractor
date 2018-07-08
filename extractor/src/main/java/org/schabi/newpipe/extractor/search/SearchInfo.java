package org.schabi.newpipe.extractor.search;

import org.schabi.newpipe.extractor.InfoItem;
import org.schabi.newpipe.extractor.ListExtractor;
import org.schabi.newpipe.extractor.ListInfo;
import org.schabi.newpipe.extractor.StreamingService;
import org.schabi.newpipe.extractor.exceptions.ExtractionException;
import org.schabi.newpipe.extractor.uih.SearchQIHandler;

import java.io.IOException;


public class SearchInfo extends ListInfo<InfoItem> {

    private String searchString;
    private String searchSuggestion;

    public SearchInfo(int serviceId,
                      SearchQIHandler qIHandler,
                      String searchString) {
        super(serviceId, qIHandler, "Search");
        this.searchString = searchString;
    }


    public static SearchInfo getInfo(SearchExtractor extractor) {
        final SearchInfo info = new SearchInfo(
                extractor.getServiceId(),
                extractor.getUIHandler(),
                extractor.getSearchString());

        try {
            info.searchSuggestion = extractor.getSearchSuggestion();
        } catch (Exception e) {
            info.addError(e);
        }

        return info;
    }


    public static ListExtractor.InfoItemsPage<InfoItem> getMoreItems(StreamingService service,
                                                                     SearchQIHandler query,
                                                                     String contentCountry,
                                                                     String pageUrl)
            throws IOException, ExtractionException {
        return service.getSearchExtractor(query, contentCountry).getPage(pageUrl);
    }

    // Getter
    public String getSearchString() {
        return searchString;
    }

    public String getSearchSuggestion() {
        return searchSuggestion;
    }
}
