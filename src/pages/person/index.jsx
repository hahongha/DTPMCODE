import MainCard from 'components/MainCard';
import PersonTable from './person';
import { Box, Button, Stack } from '@mui/material';
import { useNavigate } from 'react-router';

function Person() {
    const navigate = useNavigate();
  return (
    <MainCard>
      <Stack spacing={2}>
        <Box>
          <Button variant="contained" sx={'margin-bottom: 5'} onClick={()=>{navigate("/addPerson")}}>
            Thêm mới
          </Button>
        </Box>
        <PersonTable />
      </Stack>
    </MainCard>
  );
}

export default Person;
