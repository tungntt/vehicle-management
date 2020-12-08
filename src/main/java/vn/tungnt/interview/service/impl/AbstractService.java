package vn.tungnt.interview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.CollectionUtils;
import vn.tungnt.interview.domain.entity.BaseEntity;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.repository.CredentialRepository;
import vn.tungnt.interview.security.SecurityUtils;
import vn.tungnt.interview.service.BaseService;
import vn.tungnt.interview.service.dto.BaseDTO;
import vn.tungnt.interview.service.exception.BusinessException;
import vn.tungnt.interview.service.mapper.BaseMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractService<E extends BaseEntity, D extends BaseDTO>
        implements BaseService<E, D> {

    private final JpaRepository<E, Long> repository;

    private final BaseMapper<E, D> mapper;

    private CredentialRepository credentialRepository;

    public AbstractService(final JpaRepository<E, Long> repository, final BaseMapper<E, D> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public D add(final D d) {
        d.setCredentialId(this.getCurrentCredential().getId());
        final E e = this.create(d);
        return this.mapper.toDTO(e);
    }

    @Override
    public D edit(final D d) {
        final E e = this.update(d);
        return this.mapper.toDTO(e);
    }

    @Override
    public List<D> readAll() {
        final List<E> all = this.repository.findAll();
        if (CollectionUtils.isEmpty(all)) {
            return Collections.emptyList();
        }
        return this.mapper.toDto(all);
    }

    @Override
    public Optional<D> readById(final long id) {
        final Optional<E> optional = this.repository.findById(id);
        if (optional.isPresent()) {
            final D d = this.mapper.toDTO(optional.get());
            return Optional.of(d);
        }
        return Optional.empty();
    }

    @Override
    public D remove(final long id) {
        final D d = this.readById(id)
                .orElseThrow(() -> new BusinessException(String.format("Not found instance by id %s", id)));
        this.repository.deleteById(id);
        return d;
    }

    /**
     * Create Method
     * @param d
     * @return
     */
    protected E create(final D d) {
        return this.save(d);
    }

    /**
     * Update Method
     * @param d
     * @return
     */
    protected E update(final D d) {
        Objects.requireNonNull(d.getId(), String.format("%s id must be valuable", d.getClass().getName()));
        final boolean isExisted = this.repository.existsById(d.getId());
        if (isExisted) {
            return this.save(d);
        } else {
            throw new IllegalArgumentException(String.format("Not found %s with id %s", d.getClass().getName(), d.getId()));
        }
    }

    /**
     * Save Method
     * @param d
     * @return
     */
    private E save(final D d) {
        final E entity = this.mapper.toEntity(d);
        return this.repository.save(entity);
    }

    protected CredentialEntity getCurrentCredential() {
        final String username = SecurityUtils
                .getCurrentUserLogin()
                .orElseThrow(() -> new BusinessException("No user loggin"));
        CredentialEntity credential = this.credentialRepository.findByUserName(username)
                .orElseThrow(() -> new BusinessException("Not found user"));
        return credential;
    }

    @Autowired
    public void setCredentialRepository(final CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }
}
