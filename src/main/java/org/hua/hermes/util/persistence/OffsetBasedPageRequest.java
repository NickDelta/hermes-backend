package org.hua.hermes.util.persistence;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Pageable implementation that paginates according to offset and limit.
 * This implementation is taken from <a href="https://stackoverflow.com/a/36365522/10456481">Stack Overflow</a>
 * and modified to serve the needs of this app.
 * @author <a href="mailto:nikosdelta@protonmail.com">Nick Dimitrakopoulos</a>
 */
public class OffsetBasedPageRequest implements Pageable
{
    private final int limit;
    private final int offset;
    private final Sort sort;

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters applied.
     *
     * @param offset zero-based offset.
     * @param limit  the size of the elements to be returned.
     * @param sort   can be {@literal null}.
     */
    public OffsetBasedPageRequest(int offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero");
        }
        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one");
        }
        this.offset = offset;
        this.limit = limit;
        this.sort = sort;
    }

    /**
     * Creates a new {@link OffsetBasedPageRequest} with unsorted as Sort type.
     *
     * @param offset zero-based offset.
     * @param limit  the size of the elements to be returned.
     */
    public OffsetBasedPageRequest(int offset,int limit) {
        this(offset,limit,Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        return offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(getPageSize(), (int)(getOffset() + getPageSize()));
    }

    public Pageable previous() {
        return hasPrevious() ? new OffsetBasedPageRequest(getPageSize(), (int)(getOffset() - getPageSize())): this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(getPageSize(), 0);
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }
}