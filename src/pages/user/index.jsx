import MainCard from 'components/MainCard';
import UserTable from './user';
import { Box, Button, Stack } from '@mui/material';
import { useNavigate } from 'react-router';

function User() {
    const navigate = useNavigate();

  return (
    <MainCard>
      <Stack spacing={2}>
        <Box>
          <Button variant="contained" sx={'margin-bottom: 5'} onClick={()=>{navigate("/addUser")}} >
            Thêm mới
          </Button>
        </Box>
        <UserTable />
      </Stack>
    </MainCard>
  );
}

export default User;
