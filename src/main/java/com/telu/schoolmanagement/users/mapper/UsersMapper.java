    package com.telu.schoolmanagement.users.mapper;

    import com.telu.schoolmanagement.common.response.GeneralCreatedUpdatedBy;
    import com.telu.schoolmanagement.users.dto.UsersResponseDTO;
    import com.telu.schoolmanagement.users.model.Users;

    public class UsersMapper {
        public static UsersResponseDTO toDTO(Users users) {
            return UsersResponseDTO.builder()
                    .id(users.getId())
                    .nip(users.getNip())
                    .name(users.getName())
                    .isActive(users.getIsActive())
                    .graduateAt(users.getGraduateAt())

                    .roles(users.getRole() != null ? new UsersResponseDTO.RoleSummary(
                            users.getRole().getId(),
                            users.getRole().getName()
                    ) : null)

                    .program(users.getProgram() != null ? new UsersResponseDTO.ProgramSummary(
                            users.getProgram().getId(),
                            users.getProgram().getName()
                    ) : null)

                    .faculties(users.getFaculty() != null ? new UsersResponseDTO.FacultySummary(
                            users.getFaculty().getId(),
                            users.getFaculty().getName()
                    ) : null)

                    .createdBy(users.getCreatedBy() != null ? new GeneralCreatedUpdatedBy(
                            users.getCreatedBy().getId(),
                            users.getCreatedBy().getName()
                    ) : null)

                    .updatedBy(users.getUpdatedBy() != null ? new GeneralCreatedUpdatedBy(
                            users.getUpdatedBy().getId(),
                            users.getUpdatedBy().getName()
                    ) : null)

                    .createdAt(users.getCreatedAt())
                    .updatedAt(users.getUpdatedAt())
                    .build();
        }
    }
