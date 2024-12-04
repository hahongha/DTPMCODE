import MainCard from 'components/MainCard';

import { Box, Button, Stack } from '@mui/material';
import { useNavigate } from 'react-router';
import ServiceTable from './service';

function Service() {
    const navigate = useNavigate();
  return (
    <MainCard>
      <Stack spacing={2}>
        <Box>
          <Button variant="contained" sx={'margin-bottom: 5'} onClick={()=>{navigate("/addService")}}>
            Thêm mới
          </Button>
          <ServiceTable/>
        </Box>
        
      </Stack>
    </MainCard>
  );
}

export default Service;
